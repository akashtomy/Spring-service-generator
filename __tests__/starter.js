'use strict';
const path = require('path');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');

describe('generator-new-generator:starter', () => {
  beforeAll(() => {
    return helpers
      .run(path.join(__dirname, '../generators/starter'))
      .withPrompts({name: 'Service-Name', authentication: true});
  });

  it('creates README file and all other top level directory files', () => {
    assert.file(['clp-service-name-starter/README.md']);
    assert.file(['clp-service-name-starter/build.gradle']);
    assert.file(['clp-service-name-starter/gradle.properties']);
    assert.file(['clp-service-name-starter/gradlew.bat']);
    assert.file(['clp-service-name-starter/Jenkinsfile']);
  });

  it('creates .gitignore file', () => {
    assert.file(['clp-service-name-starter/.gitignore']);
  });

  it('creates gradle wrapper directory', () => {
    assert.file(['clp-service-name-starter/gradle/wrapper/gradle-wrapper.properties']);
  });

  it('creates src directory DTOs', () => {
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ErrorMessage.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ErrorResponse.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ExampleResponse.java']);
  });

  it('creates src directory exceptions', () => {
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java']);
  });

  it('creates src main top level classes', () => {
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ErrorHandler.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientCredentialsResourceDetails.java']);
    assert.file(['clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptor.java']);
  });

  it('creates src main resources files', () => {
    assert.file(['clp-service-name-starter/src/main/resources/config/clpservicenameservicestarter.properties']);
    assert.file(['clp-service-name-starter/src/main/resources/META-INF/spring.factories']);
  });

  it('creates src test top level classes', () => {
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java']);
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java']);
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptorTest.java']);
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java']);
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java']);
    assert.file(['clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterSampleClient.java']);
  });

  it('checks all package names for src main changed', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ErrorMessage.java',
      'package com.corelogic.clp.starters.servicename.dtos;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ErrorResponse.java',
      'package com.corelogic.clp.starters.servicename.dtos;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/dtos/ExampleResponse.java',
      'package com.corelogic.clp.starters.servicename.dtos;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java',
      'package com.corelogic.clp.starters.servicename.exceptions;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'package com.corelogic.clp.starters.servicename;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java',
      'package com.corelogic.clp.starters.servicename;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientCredentialsResourceDetails.java',
      'package com.corelogic.clp.starters.servicename;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptor.java',
      'package com.corelogic.clp.starters.servicename;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ErrorHandler.java',
      'package com.corelogic.clp.starters.servicename;'
    );
  });

  it('checks ServiceAutoConfiguration.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java',
      'public class ServiceNameServiceException'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java',
      'public ServiceNameServiceException(Throwable exception, String message)'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java',
      'public ServiceNameServiceException(Exception e) {'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/exceptions/ServiceNameServiceException.java',
      'public ServiceNameServiceException(int statusCode, String message)'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@ConditionalOnProperty(prefix = "servicenameservice", name = "url")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@PropertySource("classpath:/config/clpservicenameservicestarter.properties")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'public class ServiceNameServiceAutoConfiguration {'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'return new ServiceNameServiceClientCredentialsResourceDetails();'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'oAuth2RestTemplate.setInterceptors(Collections.singletonList(new ServiceNameServiceClientHttpRequestInterceptor(version, applicationName)));'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'public ServiceNameServiceClient serviceNameClient(@Qualifier("serviceNameServiceRestTemplate") OAuth2RestTemplate serviceNameServiceRestTemplate,'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Bean(name = "serviceNameServiceClientCredentialsResourceDetails")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Bean(name = "serviceNameServiceRestTemplate")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@ConditionalOnMissingBean(name = "serviceNameServiceRestTemplate")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'OAuth2RestTemplate restTemplate(@Qualifier("serviceNameServiceClientCredentialsResourceDetails") ClientCredentialsResourceDetails details,'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Qualifier("serviceNameServiceOAuth2ErrorHandler") OAuth2ErrorHandler oAuth2ErrorHandler,'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Bean(name = "serviceNameServiceOAuth2ErrorHandler")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@ConditionalOnMissingBean(name = "serviceNameServiceOAuth2ErrorHandler")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@ConditionalOnMissingBean(name = "serviceNameClient")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      'return new ServiceNameServiceClient(serviceNameServiceRestTemplate, serviceUrl);'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Value("${servicenameservice.url}") String serviceUrl)'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfiguration.java',
      '@Value("${clp-service-name-service-starter.build.version}") String version,'
    );
  });

  it('checks ServiceClient.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java',
      'import com.corelogic.clp.servicename.dtos.ExampleResponse.java'
    );

    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java',
      'public class ServiceNameServiceClient'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java',
      'private final Logger logger = LoggerFactory.getLogger(ServiceNameServiceClient.class);'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClient.java',
      'public ServiceNameServiceClient(OAuth2RestTemplate restTemplate, String serviceUrl) '
    );
  });

  it('checks ServiceNameServiceClientCredentialsResourceDetails.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientCredentialsResourceDetails.java',
      'public class ServiceNameServiceClientCredentialsResourceDetails extends ClientCredentialsResourceDetails'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientCredentialsResourceDetails.java',
      '@ConditionalOnMissingBean(name = "serviceNameServiceClientCredentialsResourceDetails")'
    );
  });

  it('checks ServiceNameServiceClientHttpRequestInterceptor.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptor.java',
      'public class ServiceNameServiceClientHttpRequestInterceptor implements ClientHttpRequestInterceptor'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptor.java',
      'public ServiceNameServiceClientHttpRequestInterceptor(String version, String applicationName)'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptor.java',
      'request.getHeaders().add("User-Agent", "ServiceNameServiceStarter/" + version + " (" + applicationName + ")");'
    );
  });

  it('checks ErrorHandler.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ErrorHandler.java',
      'throw new ServiceNameServiceException(statusCode, rawMessage);'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ErrorHandler.java',
      'throw new ServiceNameServiceException(e);'
    );
    assert.fileContent(
      'clp-service-name-starter/src/main/java/com/corelogic/clp/starters/servicename/ErrorHandler.java',
      'import com.corelogic.clp.starters.servicename.dtos.ErrorResponse;'
    );
  });

  it('checks spring.factories has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/resources/META-INF/spring.factories',
      'org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.corelogic.clp.starters.servicename.ServiceNameServiceAutoConfiguration'
    );
  });

  it('checks serviceAutoConfiguration has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java',
      'package com.corelogic.clp.starters.servicename;'
    );

    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java',
      'public class ServiceNameServiceAutoConfigurationTest'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java',
      'private ServiceNameServiceAutoConfiguration subject;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java',
      'subject = new ServiceNameServiceAutoConfiguration();'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceAutoConfigurationTest.java',
      'assertThat(oAuth2RestTemplate.getInterceptors().get(0)).isInstanceOf(ServiceNameServiceClientHttpRequestInterceptor.class);'
    );
  });

  it('checks ServiceNameServiceClientHttpRequestInterceptorTest has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptorTest.java',
      'package com.corelogic.clp.starters.servicename;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptorTest.java',
      'public class ServiceNameServiceClientHttpRequestInterceptorTest'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptorTest.java',
      'ServiceNameServiceClientHttpRequestInterceptor interceptor = new ServiceNameServiceClientHttpRequestInterceptor("1.0", "applicationName");'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientHttpRequestInterceptorTest.java',
      'assertThat(userAgentHeader.get(0)).isEqualTo("ServiceNameServiceStarter/1.0 (applicationName)");'
    );
  });

  it('checks ServiceNameServiceClientTest.javaTest has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java',
      'package com.corelogic.clp.starters.servicename;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java',
      'public class ServiceNameServiceClientTest {'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java',
      ' ExampleResponse response = serviceNameServiceClient.helloWorld();'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java',
      'private ServiceNameServiceClient serviceNameServiceClient;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ServiceNameServiceClientTest.java',
      'serviceNameServiceClient = new ServiceNameServiceClient(restTemplateMock, SERVICE_URL);'
    );
  });

  it('checks ErrorHandlerTest.javaTest has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'package com.corelogic.clp.starters.servicename;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'public void handleError_whenServiceNameServiceRespondsWithHttpStatus5xx_throwsServiceNameServiceException()'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'exception.expect(ServiceNameServiceException.class);'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'public void handleError_whenServiceNameServiceRespondsWithHttpStatus4xx_throwsServiceNameServiceException_withAllMessages()'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'public void handleError_whenJsonIsMalformed_throwsServiceNameServiceException_withMessages()'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/ErrorHandlerTest.java',
      'public void handleError_whenJsonIsMalformed_throwsServiceNameServiceException_withMessages()'
    );
  });

  it('checks StarterIntegrationTest.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      'package com.corelogic.clp.starters.servicename;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      '"servicenameservice.url=http://localhost:8084",'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      '"spring.application.name=ServiceNameConsumer",'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      'private ServiceNameServiceClient serviceNameServiceClient;'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      '@Qualifier("serviceNameServiceRestTemplate")'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      'assertThat(serviceNameServiceClient).isNotNull();'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      '.andExpect(header("user-agent", "ServiceNameServiceStarter/1.0.0 (ServiceNameConsumer)"))'
    );
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterIntegrationTest.java',
      '"clp-service-name-service-starter.build.version=1.0.0"'
    );
  });

  it('checks clpservicenameservicestarter.properties has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/main/resources/config/clpservicenameservicestarter.properties',
      'clp-service-name-service-starter.build.version=${version}'
    );
  });

  it('checks StarterSampleClient.java has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/src/test/java/com/corelogic/clp/starters/servicename/StarterSampleClient.java',
      'package com.corelogic.clp.starters.servicename;'
    );
  });

  it('checks README.md has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'compile(\'com.corelogic.clp.starters:clp-service-name-service-starter:{{VERSION}}\')'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'Check the [CoreLogic Nexus Repository](https://repo.corelogic.net/nexus/content/repositories/idc-repo-releases/com/corelogic/clp/starters/clp-service-name-service-starter/) to see what the latest version of the starter is.\n'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'Check the [CoreLogic Nexus Repository](https://repo.corelogic.net/nexus/content/repositories/idc-repo-releases/com/corelogic/clp/starters/clp-service-name-service-starter/) to see what the latest version of the starter is.\n'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'So you want to use Service Name Service on a Spring Boot project? Well, you\'ve come to right place!'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '### Wire in your OAuth properties and CLP Service Name Service Url'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '#####  Connecting to Other Service Name Service Environments'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'If you want to run your app locally, you can connect to the actual Service Name Service by including the `servicenameservice.url` in your `application-dev.properties` file and pointing to the Service Name Service instance in the `INT` environment, which has the latest stable version:'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '**IMPORTANT:** Make sure that your Apigee client accepts the grant-type `client_credentials` and has the `clp-service-name.application` scope.'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '#### Point to an Instance of Service Name Service'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '| CF environment | Service Name URL                            | APIGEE ACCESS TOKEN URI                                |'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '| `SB`          | <https://clp-service-name-service-sb.apps.pcfwtcdev.clgxlabs.io> | https://corelogic-np-test.apigee.net/edgemicro-auth/token |'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '| `SB`          | <https://clp-service-name-service-sb.apps.pcfwtcdev.clgxlabs.io> | https://corelogic-np-test.apigee.net/edgemicro-auth/token |'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'The starter auto-configures `ServiceNameClient` bean that could be autowired into any of your app\'s components such as:'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'private ServiceNameServiceClient serviceNameServiceClient;'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'public MyService(ServiceNameServiceClient serviceNameServiceClient)'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'this.serviceNameServiceClient = serviceNameServiceClient;'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '* The `ServiceNameServiceClient` provides the following method:'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'servicenameservice.url=SERVICE_NAME_SERVICE_URL'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '> **Note:** Please remember that **you will need to use different client credentials** for Apigee in PROD when connecting to Service Name in UAT and PROD.'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      'servicenameservice.url=https://clp-service-name-service-sb.apps.pcfwtcdev.clgxlabs.io'
    );
    assert.fileContent(
      'clp-service-name-starter/README.md',
      '# CLP Service Name Service Starter'
    );
  });
  it('checks Jenkinsfile has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/Jenkinsfile',
      'dir(\'clp-service-name-service-starter\')'
    );
  });
  it('checks build.gradle has been updated to new service names', () => {
    assert.fileContent(
      'clp-service-name-starter/build.gradle',
      'baseName = \'clp-service-name-service-starter\''
    );
    assert.fileContent(
      'clp-service-name-starter/build.gradle',
      'property \'sonar.projectKey\', \'com.corelogic.clp.starters.servicename:clp-service-name-service-starter\''
    );
    assert.fileContent(
      'clp-service-name-starter/build.gradle',
      'property \'sonar.projectName\', \'clp-service-name-service-starter\''
    );
  });
});
