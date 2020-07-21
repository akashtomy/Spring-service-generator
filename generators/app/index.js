'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const rename = require('gulp-rename');

module.exports = class extends Generator {
  initializing() {
    this.log('initializing!');
  }

  prompting() {
    // Have Yeoman greet the user.
    this.log(yosay('Welcome to the amazing ' + chalk.red('Microservice') + ' generator!'));

    const isNotEmpty = function (value) {
      if (value) {
        return true;
      }

      return 'Required Field';
    };
    const prompts = [
      {
        type: 'string',
        name: 'name',
        message:
          'What\'s the name of the service you want to create? e.g. \'service-name\' generates \'ria-service-name-service\'',
        validate: isNotEmpty
      },
      {
        type: 'string',
        name: 'serviceDescription',
        message:
          'Please provide a brief description of what your service does? e.g. This service ...{description}',
        validate: isNotEmpty
      }
    ];

    return this.prompt(prompts).then(answer => {
      // To access props later use this.props.someAnswer;
      this.props = answer;
    });
  }

  writing() {
    //Service Name
    const simpleServiceName = this.props.name.toLowrCase().replace(/ /g, '-');
    //service-name
    const fullServiceName = 'ria-' + simpleServiceName + '-service';
    const variableName = simpleServiceName.replace(/-([a-z])/g, function (g) {
      return g[1].toUpperCase();
    });
    //serviceName
    const className = variableName.replace(
      /^[a-z]/,
      variableName.charAt(0).toUpperCase()
    );//ServiceName
    const controllerName = className;
    const applicationName = className + 'Service';
    const packageName = variableName.toLowerCase();
    //servicename

    const applicationNameWithSpaces = fixApplicationName(simpleServiceName);
    const applicationNameWithSpacesURLEncoded = applicationNameWithSpaces.replace(
      / /g,
      '%20'
    );
    const directoryName = 'clp-' + this.props.name.toLowerCase() + '-service';

    this.registerTransformStream(
      rename(function (path) {
        path.basename = path.basename.replace(/(_APPLICATION_NAME_)/g, applicationName);
        path.dirname = path.dirname.replace(/(SERVICETEMPLATENAME)/g, packageName);
        path.basename = path.basename.replace(/(_CONTROLLER_NAME_)/g, controllerName);
      })
    );

    this.fs.copyTpl(this.templatePath('*.*'), this.destinationPath(directoryName), {
      simpleServiceName: simpleServiceName,
      fullServiceName: fullServiceName,
      variableName: variableName,
      packageName: packageName,
      applicationName: applicationName,
      controllerName: controllerName,
      applicationNameWithSpaces: applicationNameWithSpaces,
      applicationNameWithSpacesURLEncoded: applicationNameWithSpacesURLEncoded,
      serviceDescription: this.props.serviceDescription,
      schemaSimpleServiceName: simpleServiceName.toLowerCase().replace(/\-/g, '_')
    });

    // Copy the files in the gradle directory
    this.fs.copy(
      this.templatePath('gradlew'),
      this.destinationPath(directoryName + '/gradlew'),
      {
        globOptions: {dot: true}
      }
    );

    // Copy the files in the gradle directory
    this.fs.copy(
      this.templatePath('gradle/**'),
      this.destinationPath(directoryName + '/gradle'),
      {
        globOptions: {dot: true}
      }
    );

    // Copy the files in the images directory
    this.fs.copy(
      this.templatePath('images/**'),
      this.destinationPath(directoryName + '/images'),
      {
        globOptions: {dot: true}
      }
    );

    var ignoreArray = [];

    // Copy src
    this.fs.copyTpl(
      [this.templatePath('src/**'), '!**/static/**'],
      this.destinationPath(directoryName + '/src/'),
      {
        simpleServiceName: simpleServiceName,
        fullServiceName: fullServiceName,
        variableName: variableName,
        packageName: packageName,
        applicationName: applicationName,
        controllerName: controllerName,
        applicationNameWithSpaces: applicationNameWithSpaces,
        applicationNameWithSpacesURLEncoded: applicationNameWithSpacesURLEncoded,
        serviceDescription: this.props.serviceDescription,
        schemaSimpleServiceName: simpleServiceName.toLowerCase().replace(/\-/g, '_')
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );

    // Copy docker
    this.fs.copyTpl(
      this.templatePath('docker/**'),
      this.destinationPath(directoryName + '/docker/'),
      {
        fullServiceName: fullServiceName,
        applicationName: applicationName,
        schemaSimpleServiceName: simpleServiceName.toLowerCase().replace(/\-/g, '_')
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );

    // Copy manifests
    this.fs.copyTpl(
      this.templatePath('manifests/**'),
      this.destinationPath(directoryName + '/manifests/'),
      {
        fullServiceName: fullServiceName,
        applicationName: applicationName
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );

    // Copy Jmeter
    this.fs.copyTpl(
      this.templatePath('jmeter/**'),
      this.destinationPath(directoryName + '/jmeter/'),
      {
        fullServiceName: fullServiceName,
        applicationName: applicationName
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );
    // Copy jenkinsfile
    this.fs.copyTpl(
      this.templatePath('Jenkinsfile'),
      this.destinationPath(directoryName + '/Jenkinsfile'),
      {
        fullServiceName: fullServiceName,
        applicationName: applicationName
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );

    // Copy .gitignore
    this.fs.copyTpl(
      this.templatePath('.gitignore'),
      this.destinationPath(directoryName + '/.gitignore'),
      {
        fullServiceName: fullServiceName,
        applicationName: applicationName
      },
      {},
      {
        globOptions: {ignore: ignoreArray}
      }
    );

    // Copy the files in the static directory
    this.fs.copy(
      this.templatePath('src/main/resources/static/**'),
      this.destinationPath(directoryName + '/src/main/resources/static'),
      {
        globOptions: {dot: true}
      }
    );
  }
};

function fixApplicationName(inputName) {
  var fixedAppName = inputName
    .replace(/\w*/g, function (txt) {
      return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    })
    .replace(/-/g, ' ');
  return fixedAppName;
}
