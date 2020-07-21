'use strict';
const path = require('path');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');

describe('generator-new-generator:app', () => {
  beforeAll(() => {
    return helpers
      .run(path.join(__dirname, '../generators/app'))
      .withPrompts({name: 'Service-Name'});
  });

  it('creates README file', () => {
    assert.file(['clp-service-name-service/README.md']);
  });

  it('creates gradle files', () => {
    assert.file(['clp-service-name-service/build.gradle']);
    assert.file(['clp-service-name-service/gradlew']);
  });

  it('creates .gitignore file', () => {
    assert.file(['clp-service-name-service/.gitignore']);
  });

  it('creates PerformanceTest.jmx file', () => {
    assert.file(['clp-service-name-service/jmeter/PerformanceTest.jmx']);
  });

  it('creates Jenkinsfile file', () => {
    assert.file(['clp-service-name-service/Jenkinsfile']);
  });

  it('creates cucumber files', () => {
    assert.file(['clp-service-name-service/src/cucumber/java/com/corelogic/clp/servicename/api/AccessTokenResponse.java']);
    assert.file(['clp-service-name-service/src/cucumber/java/com/corelogic/clp/servicename/api/CucumberTest.java']);
    assert.file(['clp-service-name-service/src/cucumber/java/com/corelogic/clp/servicename/api/HelloWorldSteps.java']);
    assert.file(['clp-service-name-service/src/cucumber/resources/features/helloWorld.feature']);
    assert.file(['clp-service-name-service/src/cucumber/resources/application.properties']);
  });

  it('creates configuration files', () => {
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/SecurityConfiguration.java'
    ]);
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/LocalAuthorizationServerConfiguration.java'
    ]);
  });

  it('creates DTO files', () => {
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/dtos/ErrorMessage.java'
    ]);
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/dtos/ErrorResponse.java'
    ]);
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/dtos/ExampleResponse.java'
    ]);
  });

  it('creates GlobalExceptionHandler file', () => {
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/exceptions/GlobalExceptionHandler.java'
    ]);
  });

  it('creates Application and Controller files', () => {
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplication.java'
    ]);
    assert.file([
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameController.java'
    ]);
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplication.java',
      'ServiceName'
    );
  });

  it('checks all package names changed', () => {
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/LocalAuthorizationServerConfiguration.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/SecurityConfiguration.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/dtos/ErrorMessage.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/dtos/ErrorResponse.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/exceptions/GlobalExceptionHandler.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/SecurityConfiguration.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/services/ServiceNameService.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplication.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplicationTest.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameController.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerTest.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerIntegrationTest.java',
      'servicename'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/util/OAuth2Helper.java',
      'servicename'
    );
  });

  it('checks all full service names changed in configuration', () => {
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/LocalAuthorizationServerConfiguration.java',
      'clp-service-name-service'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/configuration/SecurityConfiguration.java',
      'clp-service-name-service'
    );
  });

  it('checks all application names changed in application', () => {
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/services/ServiceNameService.java',
      'ServiceNameService'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplication.java',
      'ServiceNameService'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameServiceApplicationTest.java',
      'ServiceNameService'
    );
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameController.java',
      'ServiceNameService'
    );
  });

  it('checks all controller names changed in application', () => {
    assert.fileContent(
      'clp-service-name-service/src/main/java/com/corelogic/clp/servicename/api/ServiceNameController.java',
      'ServiceNameController'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerTest.java',
      'ServiceNameController'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerIntegrationTest.java',
      'ServiceNameController'
    );
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerTest.java',
      'ServiceNameController'
    );
  });

  it('checks all variable names changed in application', () => {
    assert.fileContent(
      'clp-service-name-service/src/test/java/com/corelogic/clp/servicename/api/ServiceNameControllerTest.java',
      'serviceNameController'
    );
  });
});
