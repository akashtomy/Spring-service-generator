# clp-service-generator [![NPM version][npm-image]][npm-url] [![Build Status][travis-image]][travis-url] [![Dependency Status][daviddm-image]][daviddm-url]

This generates a service with the endpoint `/helloWorld` with a full suite of tests and security. This follows the Service Conventions found in the [CLP-Conventions Wiki](https://github.com/corelogic/clp-conventions/wiki).

## Mac OSX Installation

### Prerequisites
Make sure you have installed docker.
To verify you have docker you can do the following:
`docker -v`
 
Otherwise run the following command to install docker:
```bash
brew install docker
```
### Installation
First, clone code from Github repository and move into the created directory:
```bash
git clone https://github.com/corelogic/clp-service-generator.git
cd clp-service-generator
```
yo.sh help
```bash
Welcome to the CLP Service Generator, for all your service generation needs.
Running this command with no parameters will result in the default behavior and generate a service

   -h, --help              Show help
   -s, --starters          This will generate a starter
   -r, --refresh           This will refresh the service generated
                           (Only needed if the CLP Service generator has changed)
```

Next, run ./yo.sh or ./yo.sh -s
```bash
./yo.sh
```

You will be prompted to add your service name, following clp service conventions: 
```bash
clp-{insert-me}-service 
```    
For example, entering `service-name` will create a directory called `clp-service-name-service`

The next prompt will ask you to add a description that will be added to the generated README.md file.

**Your new service will be created in the directory where you cloned the `clp-service-generator` into.**

### Create Service/Starter

To generate a service or starter with out docker do the following:
```
  yo createService          #Creates Service 
  yo createService:starter  #Creates Starter
```

### Updating the Generator to the latest version
Make sure you have the latest code by doing the following:
```
cd ~/clp-service-generator
git pull -r
```

Next remove the old docker image from your local machine so it will be updated the next time you generate a service:
```
./yo.sh -r 
```

## Usage

To access the `/helloWorld` endpoint, an access token is required. To obtain an access token, do a POST request to `localhost:8084/oauth/token?grant_type=client_credentials` 
using Basic Auth with username: `fake-client` and password: `secret`. These tokens are cached for 10 minutes, but the duration can be changed by changing the `cache.evict.milliseconds` property in 
the `application.properties` file.

Example cURL request:
```bash
curl -X POST   'http://localhost:8084/oauth/token?grant_type=client_credentials' \
    -H 'accept: application/json' \
    -H 'authorization: Basic c2FtcGxlLWNsaWVudDpzZWNyZXQ='
```

Then run a GET request to `localhost:8084/helloWorld` with Header `Authorization` `Bearer {{token}}`  

Example cURL request:
```bash
curl -X GET \
  http://localhost:8084/helloWorld \
  -H 'authorization: Bearer {{token}}'
```
**NOTE** Make sure to replace `{{token}}` with your bearer token

## CUCUMBER ACCEPTANCE TEST
  
To use cucumber test running `./gradlew cucumber` command is required.
It uses gradle-cucumber plugin to execute tests described in *.feature files.

**NOTE** Make sure to replace `INSERT_ME` values in cucumber resources application.properties in order to run it on desired environment.
**NOTE** You can run cucumber tests in different environments by creating applicable `application-<environmentName>.properties` and adding `SPRING_PROFILES_ACTIVE=<environmentName>` prior to `./gradlew cucumber` command.
 

## Generator tests
The generator is tested through the `app.js` file and uses Jest for its Javascript testing.

To run all tests type the following in the command line:
```bash
$ cd ~/clp-service-generator
$ npm test
```
This will look for the `package.json`. The `package.json` is located in the `clp-service-generator` directory.

##Postman Collections and Environments
The service generator yeoman generator will create an initial postman collection called `Generated Service Example` and environment variables (ie. SERVICE_NAME Integration) for the RESTdocs Postman buttons.


## Getting To Know Yeoman

 * Yeoman can be too opinionated at times but is easily convinced not to be.
 * Feel free to [learn more about Yeoman](http://yeoman.io/).

[npm-image]: https://badge.fury.io/js/generator-jacktest.svg
[npm-url]: https://npmjs.org/package/generator-jacktest
[travis-image]: https://travis-ci.org/CoreLogic/generator-jacktest.svg?branch=master
[travis-url]: https://travis-ci.org/CoreLogic/generator-jacktest
[daviddm-image]: https://david-dm.org/CoreLogic/generator-jacktest.svg?theme=shields.io
[daviddm-url]: https://david-dm.org/CoreLogic/generator-jacktest
