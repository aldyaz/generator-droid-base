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
  console.log('Setting up codebase…');

  const PLACEHOLDER = "_placeholder_"
  const configReplace = [
    {
      symbol: '<%= appPackage %>',
      replace: 'com.example.app',
      replacement: `${PLACEHOLDER}`
    },
    {
      symbol: '<%= appName %>',
      replace: '<string name="app_name">SampleApp</string>',
      replacement: `<string name="app_name">${PLACEHOLDER}</string>`
    },
    {
      symbol: '<%= appName %>',
      replace: 'rootProject.name = "Skeleton Android Project"',
      replacement: `rootProject.name = "${PLACEHOLDER}"`
    },
    {
      symbol: '<%= androidTargetSdkVersion %>',
      replace: 'compileSdk = "33"',
      replacement: `compileSdk = ${PLACEHOLDER}`
    },
    {
      symbol: '<%= androidTargetSdkVersion %>',
      replace: 'targetSdk = "33"',
      replacement: `targetSdk = ${PLACEHOLDER}`
    },
    {
      symbol: '<%= androidMinSdkVersion %>',
      replace: 'minSdk = "21"',
      replacement: `minSdk = ${PLACEHOLDER}`
    }
  ]

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
