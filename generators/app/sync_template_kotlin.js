import path from 'path';
import rimrafBase from 'rimraf';
import sgit from 'simple-git';
import mv from 'mv';
import replace from 'replace';
import ncpBase from 'ncp';
import { fileURLToPath } from 'url';

const { join } = path;
const { sync } = rimrafBase;
const { ncp } = ncpBase;

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const tempDir = join(__dirname, './tmp');
const appPath = 'base';

console.log('Running… ');

sync(tempDir);

sgit().clone('https://github.com/aldyaz/skeleton-android-project.git', tempDir)
  .then(async function () {
    return clearTemplate().then(() => checkOutAndCopy());
  })
  .catch(function (err) {
    console.log(err);
  });

function clearTemplate() {
  return new Promise(resolve => {
    sync(join(__dirname, `/templates/${appPath}/*`));
    sync(join(__dirname, `/templates/${appPath}/.*`));
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

  mv(tempDir + '/domain/.gitignore', tempDir + '/domain/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed domain folder .gitignore');
  });

  mv(tempDir + '/data/.gitignore', tempDir + '/data/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed data folder .gitignore');
  });

  mv(tempDir + '/buildSrc/.gitignore', tempDir + '/buildSrc/gitignore', function (err) {
    if (err) {
      console.log(err);
    }
    console.log('Renamed data folder .gitignore');
  });

  sync(join(__dirname, '/tmp/.git'));

  console.log('Copying files to ./templates/' + appPath);

  ncp.limit = 1600;
  ncp(tempDir, join(__dirname, 'templates/' + appPath), function (err) {
    if (err) {
      return console.error(err);
    }
    console.log('Copying complete!');
    sync(tempDir);
  });
}
