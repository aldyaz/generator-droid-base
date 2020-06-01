'use strict';

const path = require('path');
const rimraf = require('rimraf');
const sgit = require('simple-git/promise');
const mv = require('mv');
const replace = require('replace');
const ncp = require('ncp').ncp;

const tempDir = path.join(__dirname, './tmp');
const appPath = 'base';

console.log('Running… ');

rimraf.sync(tempDir);

sgit().clone('https://github.com/aldyaz/skeleton-android-project.git', tempDir)
  .then(async function () {
    return clearTemplate().then(() => checkOutAndCopy());
  })
  .catch(function (err) {
    console.log(err);
  });

function clearTemplate() {
  return new Promise(resolve => {
    rimraf.sync(path.join(__dirname, `/templates/${appPath}/*`));
    rimraf.sync(path.join(__dirname, `/templates/${appPath}/.*`));
    resolve();
  });
}
  
function checkOutAndCopy() {
  console.log('Setting up code base…');

  const PLACEHOLDER = "_placeholder_"
  const configReplace = [
    {
      symbol: '<%= appName %>',
      replace: '<string name="app_name">SampleApp</string>',
      replacement: `<string name="app_name">${PLACEHOLDER}</string>`
    },
    {
      symbol: '<%= androidTargetSdkVersion %>',
      replace: 'compileSdkVersion = 28',
      replacement: `compileSdkVersion = ${PLACEHOLDER}`
    },
    {
      symbol: '<%= androidMinSdkVersion %>',
      replace: 'minSdkVersion = 19',
      replacement: `minSdkVersion = ${PLACEHOLDER}`
    }
  ]

  replace({
    regex: 'com.example.app',
    replacement: '<%= appPackage %>',
    paths: [tempDir],
    recursive: true,
    silent: true
  });

  configReplace.forEach(config => {
    replace({
      regex: config.replace,
      replacement: config.replacement.replace(PLACEHOLDER, config.symbol),
      paths: [tempDir],
      recursive: true,
      silent: true
    })  
  })

  mv(tempDir + '/.gitignore', tempDir + '/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed root folder .gitignore');
  });

  mv(tempDir + '/app/.gitignore', tempDir + '/app/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed app folder .gitignore');
  });

  rimraf.sync(path.join(__dirname, '/tmp/.git'));

  console.log('Copying files to ./templates/' + appPath);

  ncp.limit = 1600;
  ncp(tempDir, path.join(__dirname, 'templates/' + appPath), function (err) {
    if (err) {
      return console.error(err);
    }
    console.log('Copying complete!');
    rimraf.sync(tempDir);
  });
}
