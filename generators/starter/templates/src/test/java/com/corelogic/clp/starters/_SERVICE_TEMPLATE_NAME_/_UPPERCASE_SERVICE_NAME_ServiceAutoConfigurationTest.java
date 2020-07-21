package com.corelogic.clp.starters.<%=packageName%>;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class <%=properCaseName%>ServiceAutoConfigurationTest {

    private <%=properCaseName%>ServiceAutoConfiguration subject;

    @Mock
    private ClientCredentialsResourceDetails mockClientCredentialsResourceDetails;

    @Mock
    private OAuth2ErrorHandler mockOAuth2ErrorHandler;

    @Before
    public void setUp() throws Exception {
        subject = new <%=properCaseName%>ServiceAutoConfiguration();
    }

    @Test
    public void restTemplate_isOnlyConfiguredForJsonConverter() throws Exception {
        OAuth2RestTemplate oAuth2RestTemplate = subject.restTemplate(mockClientCredentialsResourceDetails, mockOAuth2ErrorHandler, "version", "applicationName");

        assertThat(oAuth2RestTemplate.getMessageConverters()).hasSize(1);
        assertThat(oAuth2RestTemplate.getMessageConverters().get(0)).isInstanceOf(MappingJackson2HttpMessageConverter.class);
    }

    @Test
    public void restTemplate_hasCustomRequestInterceptor() throws Exception {
        OAuth2RestTemplate oAuth2RestTemplate = subject.restTemplate(mockClientCredentialsResourceDetails, mockOAuth2ErrorHandler, "version", "applicationName");

        assertThat(oAuth2RestTemplate.getInterceptors()).hasSize(1);
        assertThat(oAuth2RestTemplate.getInterceptors().get(0)).isInstanceOf(<%=properCaseName%>ServiceClientHttpRequestInterceptor.class);
    }

    @Test
    public void restTemplate_hasOAuth2ErrorHandler() {
        OAuth2RestTemplate oAuth2RestTemplate = subject.restTemplate(mockClientCredentialsResourceDetails, mockOAuth2ErrorHandler, "version", "applicationName");

        assertThat(oAuth2RestTemplate.getErrorHandler()).isInstanceOf(OAuth2ErrorHandler.class);
    }

    @Test
    public void restTemplate_hasSimpleClientRequestFactory_withOutputStreamingFalse() {
        OAuth2RestTemplate oAuth2RestTemplate = subject.restTemplate(mockClientCredentialsResourceDetails, mockOAuth2ErrorHandler, "version", "applicationName");

        ClientHttpRequestFactory interceptingRequestFactory = oAuth2RestTemplate.getRequestFactory();
        SimpleClientHttpRequestFactory simpleRequestFactory = (SimpleClientHttpRequestFactory) ReflectionTestUtils.getField(interceptingRequestFactory, "requestFactory");
        boolean outputStreaming = (boolean) ReflectionTestUtils.getField(simpleRequestFactory, "outputStreaming");

        assertFalse(outputStreaming);
    }
}
