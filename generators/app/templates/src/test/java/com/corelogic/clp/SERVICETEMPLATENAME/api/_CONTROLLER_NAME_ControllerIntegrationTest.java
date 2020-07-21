package com.corelogic.clp.<%=packageName%>.api;

import com.corelogic.clp.<%=packageName%>.api.util.OAuth2Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = <%=applicationName%>Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class <%=controllerName%>ControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    OAuth2Helper oAuth2Helper;

    @Test
    public void helloWorld_whenAuthorized_returns200HelloWorld() throws Exception {
        String bearerToken = oAuth2Helper.createAccessToken("fake-client").getValue();

        mockMvc.perform(get("/helloWorld")
                .header("Authorization", "Bearer " + bearerToken))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello World!\"}"));
    }
}
