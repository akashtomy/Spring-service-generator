'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const rename = require('gulp-rename');

module.exports = class extends Generator {
  initializing() {
    this.log('Initializing Starter generator...');
  }

  prompting() {
    this.log(yosay('Generate your starter for your service!'));

    const isNotEmpty = function (value) {
      if (value) {
        return true;
      }

      return 'Please answer the question';
    };

    const prompts = [
      {
        type: 'string',
        name: 'name',
        message:
          'What\'s the name of the starter you want to create? e.g. \'service-name\' generates \'clp-service-name-service-starter\'',
        validate: isNotEmpty
      },
      {
        type: 'confirm',
        name: 'authentication',
        message: 'Do you need authentication with a third party? ie. Apigee',
        validate: isNotEmpty
      }
    ];

    return this.prompt(prompts).then(answer => {
      this.props = answer;
    });
  }

  writing() {
    const serviceName = this.props.name.toLowerCase()
      .replace(/-/g, ' ')
      .replace(/^[a-z]/, this.props.name.charAt(0).toUpperCase())
      .replace(/\s([a-z])/g, function (letters) {
        return ' ' + letters[1].toUpperCase();
      });//Service Name
    const properCaseName = serviceName.replace(/ /g,'');//ServiceName
    const packageName = properCaseName.toLowerCase(); //servicename
    const camelCaseName = properCaseName.replace(/^[A-Z]/, properCaseName.charAt(0).toLowerCase());//serviceName
    const lowerCaseName = this.props.name.toLowerCase().replace(/ /g, '-');//service-name
    const directoryName = 'clp-' + lowerCaseName + '-service-starter';//clp-service-name-service-starter
    const upperCaseName = serviceName.toUpperCase().replace(/ /g, '_'); //SERVICE-NAME

    this.registerTransformStream(
      rename(function (path) {
        path.dirname = path.dirname.replace(/(_SERVICE_TEMPLATE_NAME_)/g, packageName);
        path.basename = path.basename.replace(/(_SERVICE_TEMPLATE_NAME_)/g, packageName);
        path.basename = path.basename.replace(/(_UPPERCASE_SERVICE_NAME_)/g, properCaseName);
      })
    );

    this.fs.copyTpl(
      this.templatePath('*.*'),
      this.destinationPath(directoryName),
      {
        packageName: packageName,
        properCaseName: properCaseName,
        camelCaseName: camelCaseName,
        lowerCaseName: lowerCaseName,
        upperCaseName: upperCaseName,
        serviceName: serviceName
      }
    );

    this.fs.copy(
      this.templatePath('gradlew'),
      this.destinationPath(directoryName + '/gradlew'),
      {
        globOptions: {dot: true}
      }
    );

    this.fs.copy(
      this.templatePath('gradle'),
      this.destinationPath(directoryName + '/gradle')
    );

    this.fs.copyTpl(
      this.templatePath('src'),
      this.destinationPath(directoryName + '/src'),
      {
        packageName: packageName,
        properCaseName: properCaseName,
        camelCaseName: camelCaseName,
        lowerCaseName: lowerCaseName
      }
    );

    this.fs.copyTpl(
      this.templatePath('Jenkinsfile'),
      this.destinationPath(directoryName + '/Jenkinsfile'),
      {
        lowerCaseName: lowerCaseName
      }
    );

    this.fs.copy(
      this.templatePath('.gitignore'),
      this.destinationPath(directoryName + '/.gitignore')
    );
  }
};
