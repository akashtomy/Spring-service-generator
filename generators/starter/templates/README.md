# CLP <%=serviceName%> Service Starter

So you want to use <%=serviceName%> Service on a Spring Boot project? Well, you've come to right place!

## Latest Release Versions

### 1.0.0 RELEASE
---

## How to Set Up the Starter

If you are integrating using a **Spring Boot** application, you can pull in this Starter for easy consumption by pulling from the CoreLogic Nexus Repository in your `build.gradle` as follows:

```groovy
buildscript {
    repositories {
        maven { url "https://repo.corelogic.net/nexus/content/groups/m2/" }
    }
}
  
[...]
  
dependencies {
	compile('com.corelogic.clp.starters:clp-<%=lowerCaseName%>-service-starter:{{VERSION}}')
}
```

Check the [CoreLogic Nexus Repository](https://repo.corelogic.net/nexus/content/repositories/idc-repo-releases/com/corelogic/clp/starters/clp-<%=lowerCaseName%>-service-starter/) to see what the latest version of the starter is.

> **Note:** See the documentation [here](../README.md#Import-Multiple-Starters) for information on importing multiple starters.


### Wire in your OAuth properties and CLP <%=serviceName%> Service Url

If this is the first time you are working with CoreLogic Services, you must obtain service credentials for your application by requesting them from the CoreLogic Operations Team on their [Slack channel](https://clgx-idc.slack.com/messages/ask-dev-n-ops/). You will need a CF Apigee Client registered and will need to include the following properties in your configuration:

```properties
clpservices.oauth2.client.client-id=YOUR_CLIENT_ID
clpservices.oauth2.client.client-secret=YOUR_CLIENT_SECRET
clpservices.oauth2.client.access-token-uri=APIGEE_URI

<%=packageName%>service.url=<%=upperCaseName%>_SERVICE_URL
```

**IMPORTANT:** Make sure that your Apigee client accepts the grant-type `client_credentials` and has the `clp-<%=lowerCaseName%>.application` authority.

#### Point to an Instance of <%=serviceName%> Service

If you want to run your app locally, you can connect to the actual <%=serviceName%> Service by including the `<%=packageName%>service.url` in your `application-dev.properties` file and pointing to the <%=serviceName%> Service instance in the `INT` environment, which has the latest stable version:

```properties
<%=packageName%>service.url=https://clp-<%=lowerCaseName%>-service-sb.apps.pcfwtcdev.clgxlabs.io
clpservices.oauth2.client.access-token-uri=https://corelogic-np-test.apigee.net/edgemicro-auth/token
```

#####  Connecting to Other <%=serviceName%> Service Environments
| CF environment | <%=serviceName%> URL                            | APIGEE ACCESS TOKEN URI                            |
| -------------- | ------------------------------------------ | --------------------------------------------------- | 
| `SB`          | <https://clp-<%=lowerCaseName%>-service-sb.apps.pcfwtcdev.clgxlabs.io> | https://corelogic-np-test.apigee.net/edgemicro-auth/token |

> **Note:** Please remember that **you will need to use different client credentials** for Apigee in PROD when connecting to <%=serviceName%> in UAT and PROD.

## Access Token Validation

Example of token validation URI definition in Cloud Config:

```
security:
  oauth2:
    resource:
      jwk:
        key-set-uri: https://corelogic-np-test.apigee.net/edgemicro-auth/jwkPublicKeys
```

 CF environment          | APIGEE VALIDATE TOKEN URI                   
| -------------- | ------------------------------------------ | 
| `INT/preprod`  | https://corelogic-np-test.apigee.net/edgemicro-auth/jwkPublicKeys | 
| -------------- | ------------------------------------------------------------------------------------------ | 
| `UAT`          | https://corelogic-uat.apigee.net/edgemicro-auth/jwkPublicKeys| 
| -------------- | ------------------------------------------------------------------------------------------ |  
| `PROD`         | https://corelogic-prod.apigee.net/edgemicro-auth/jwkPublicKeys | 



## How to Use the Starter

The starter auto-configures `<%=properCaseName%>Client` bean that could be autowired into any of your app's components such as:
 
```java 
@Service
public class MyService {
    
   private <%=properCaseName%>ServiceClient <%=camelCaseName%>ServiceClient;

   @Autowired
   public MyService(<%=properCaseName%>ServiceClient <%=camelCaseName%>ServiceClient) {
       this.<%=camelCaseName%>ServiceClient = <%=camelCaseName%>ServiceClient;
       }
   ...
}
```

### Example Usage:
--

* The `<%=properCaseName%>ServiceClient` provides the following method:

	1.

