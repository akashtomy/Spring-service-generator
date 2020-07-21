package com.corelogic.clp.<%=packageName%>.api;

import com.corelogic.clp.starters.chargeback.ChargeBackServiceClient;
import com.corelogic.clp.starters.chargeback.dtos.ChargeBackRequest;
import com.corelogic.clp.<%=packageName%>.api.dtos.ExampleResponse;
import com.corelogic.clp.<%=packageName%>.api.services.<%=applicationName%>;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@RestController
@Validated
public class <%=controllerName%>Controller {

    private final <%=applicationName%> service;
    private final ChargeBackServiceClient chargeBackServiceClient;
    private static final String SERVICE_NAME = "<%=fullServiceName%>";
    private static final String NO_CLIENT_ID = "NO CLIENT ID";

    public <%=controllerName%>Controller(<%=applicationName%> service, ChargeBackServiceClient chargeBackServiceClient) {
        this.service = service;
        this.chargeBackServiceClient = chargeBackServiceClient;
    }

    @GetMapping(value = "/helloWorld")
    public ExampleResponse helloWorld() {
        ExampleResponse response = new ExampleResponse();
        response.setMessage(service.process("Hello World!"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String clientId = auth == null ? NO_CLIENT_ID : ((OAuth2Authentication) auth).getOAuth2Request().getClientId();

        ChargeBackRequest chargeBackRequest = ChargeBackRequest.builder()
                .service(SERVICE_NAME)
                .endpoint("/helloWorld")
                .httpStatus(HttpStatus.OK.value())
                .clientId(clientId)
                .build();

        chargeBackServiceClient.chargeBack(chargeBackRequest);

        return response;
    }
}
