'use strict';

const Generator = require('yeoman-generator');
const mkdirp = require('mkdirp');
const yosay = require('yosay');
const chalk = require('chalk');

module.exports = class extends Generator {

  constructor(args, opts) {
      super(args,opts);
  }

  async prompting() {
    this.props = {};

    this.log(yosay(
      'Welcome to ' + chalk.red('Skeleton Android Project') + ' generator!'
    ));

    const prompts = [
      {
        name: 'name',
        message: 'What are you calling your app?',
        store: true,
        default: this.appname
      },
      {
        name: 'package',
        message: 'What package will you be publishing the app under?',
        default: 'com.example.app'
      },
      {
        name: 'targetSdk',
        message: 'What Android SDK will you be targeting?',
        store: true,
        default: 29
      },
      {
        name: 'minSdk',
        message: 'What is the minimum Android SDK you wish to support?',
        store: true,
        default: 21
      }];

    return this.prompt(prompts).then(props => {
      this.props.appPackage = props.package;
      this.props.appName = props.name;
      this.props.androidTargetSdkVersion = props.targetSdk;
      this.props.androidMinSdkVersion = props.minSdk;
    });
  }

  writing() {
    var packageDir = this.props.appPackage.replace(/\./g, '/');
    var appFolder = 'base';

    mkdirp('app');
    mkdirp('app/src/main/assets');
    mkdirp('app/src/main/kotlin/' + packageDir);
    mkdirp('app/src/androidTest/kotlin/' + packageDir);
    mkdirp('app/src/debug');
    mkdirp('app/src/debug/kotlin/' + packageDir);
    mkdirp('app/src/release');
    mkdirp('app/src/release/kotlin/' + packageDir);
    mkdirp('app/src/test/resources');
    mkdirp('app/src/test/kotlin/' + packageDir);

    var appPath = this.sourceRoot() + '/' + appFolder + '/';

    this.fs.copy(appPath + 'gitignore', '.gitignore');
    this.fs.copy(appPath + 'gradle.properties', 'gradle.properties');
    this.fs.copy(appPath + 'gradlew', 'gradlew');
    this.fs.copy(appPath + 'gradlew.bat', 'gradlew.bat');
    this.fs.copy(appPath + 'settings.gradle', 'settings.gradle');
    this.fs.copy(appPath + 'app/gitignore', 'app/.gitignore');

    this.fs.copy(appPath + 'gradle', 'gradle');
    this.fs.copy(appPath + 'app/src/main/res', 'app/src/main/res');

    const currentPath = 'com/example/app'

    this.fs.copyTpl(appPath + 'buildconfig/dependencies.gradle', 'buildconfig/dependencies.gradle', this.props);

    this.fs.copyTpl(appPath + 'app/proguard-rules.pro', 'app/proguard-rules.pro', this.props);
    
    this.fs.copyTpl(appPath + 'README.md', 'README.md', this.props);
    this.fs.copyTpl(appPath + 'build.gradle', 'build.gradle', this.props);
    this.fs.copyTpl(appPath + 'app/build.gradle', 'app/build.gradle', this.props);

    this.fs.copyTpl(appPath + 'app/src/androidTest/kotlin/' + currentPath, 'app/src/androidTest/kotlin/' + packageDir, this.props);
    this.fs.copyTpl(appPath + 'app/src/main/AndroidManifest.xml', 'app/src/main/AndroidManifest.xml', this.props);
    this.fs.copyTpl(appPath + 'app/src/main/kotlin/' + currentPath, 'app/src/main/kotlin/' + packageDir, this.props);
    this.fs.copyTpl(appPath + 'app/src/main/res/layout', 'app/src/main/res/layout', this.props);
    this.fs.copyTpl(appPath + 'app/src/test/kotlin/' + currentPath, 'app/src/test/kotlin/' + packageDir, this.props);
  }

  end() {
    this.log(yosay(chalk.blue('Successfully creating project!')));
  }
};
