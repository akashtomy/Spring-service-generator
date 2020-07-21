package gradle.cucumber;

import com.corelogic.clp.<%=packageName%>.api.<%=applicationName%>Application;
import com.corelogic.clp.<%=packageName%>.api.dtos.ExampleResponse;
import com.corelogic.clp.<%=packageName%>.api.dtos.AccessTokenResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = <%=applicationName%>Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = <%=applicationName%>Application.class, loader = SpringBootContextLoader.class)
@ActiveProfiles("test")
public class <%=applicationName%>Steps {

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${service.url}")
    private String serviceUrl;
    @Value("${service.client-secret}")
    private String clientSecret;
    @Value("${service.client-id}")
    private String clientId;
    @Value("${service.token.url}")
    private String accessTokenUrl;

    private HttpEntity helloWorldHttpEntity;

    private ResponseEntity<ExampleResponse> helloWorldResponseEntity;

    @Given("^valid bearer token$")
    public void callHelloWorldToken() {
        HttpHeaders helloWorldTokenHeaders = new HttpHeaders();
        helloWorldTokenHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        helloWorldTokenHeaders.add("Accept", "application/json");
        HttpEntity tokenHttpEntity = new HttpEntity<>(helloWorldTokenHeaders);

        ResponseEntity<AccessTokenResponse> accessTokenResponseEntity =
        restTemplate.exchange(accessTokenUrl + "?grant_type=client_credentials",
                        HttpMethod.POST,
                        tokenHttpEntity,
                        AccessTokenResponse.class);

        AccessTokenResponse accessTokenResponse = accessTokenResponseEntity.getBody();
        HttpHeaders helloWorldHeader = new HttpHeaders();
        String accessToken = accessTokenResponse.getAccessToken();
        helloWorldHeader.add("Authorization", "Bearer " + accessToken);
        helloWorldHttpEntity = new HttpEntity<>(helloWorldHeader);
    }

    @When("^I call HelloWorld endpoint$")
    public void callHelloWorld() {
        String url = serviceUrl + "/helloWorld";
        helloWorldResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                helloWorldHttpEntity,
                ExampleResponse.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void theClientReceivesResponseStatusCodeOf(int statusCode) {
        assertThat(helloWorldResponseEntity.getStatusCode().value()).isEqualTo(statusCode);
    }
}
