package com.corelogic.clp.<%=packageName%>.api;

import com.corelogic.clp.<%=packageName%>.api.dtos.ExampleResponse;
import com.corelogic.clp.<%=packageName%>.api.services.<%=applicationName%>;
import com.corelogic.clp.starters.chargeback.ChargeBackServiceClient;
import com.corelogic.clp.starters.chargeback.dtos.ChargeBackRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class <%=controllerName%>ControllerTest {

    private <%=controllerName%>Controller <%=variableName%>Controller;
    private ChargeBackServiceClient chargeBackServiceClient;

    @Before
    public void setUp() {
        <%=applicationName%> service = new <%=applicationName%>();
        chargeBackServiceClient = mock(ChargeBackServiceClient.class);
        <%=variableName%>Controller = new <%=controllerName%>Controller(service, chargeBackServiceClient);
    }

    @Test
    public void helloWorld_onGetEndpoint_returnHelloWorld() {
        ExampleResponse response = <%=variableName%>Controller.helloWorld();
        assertThat(response.getMessage()).isEqualTo("Hello World!");

        verify(chargeBackServiceClient, times(1)).chargeBack(any(ChargeBackRequest.class));
    }
}
