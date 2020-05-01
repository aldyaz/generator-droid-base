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
  .then(function () {
    return clearTemplate().then(() => checkOutAndCopy());
  })
  .catch(function (err) {
    console.log(err);
  });

function checkOutAndCopy() {
  console.log('Setting up code base…');

  replace({
    regex: 'com.example.app',
    replacement: '<%= appPackage %>',
    paths: [tempDir],
    recursive: true,
    silent: true
  });

  mv('/.gitignore', '/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed root folder .gitignore');
  });

  mv('/app/.gitignore', '/app/gitignore', function (err) {
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

function clearTemplate() {
  return new Promise(resolve => {
    rimraf.sync(path.join(__dirname, `/templates/${appPath}/*`));
    rimraf.sync(path.join(__dirname, `/templates/${appPath}/.*`));
    resolve();
  });
}
