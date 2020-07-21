package com.corelogic.clp.<%=packageName%>.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= <%=applicationName%>Application.class)
@ActiveProfiles("test")
public class <%=applicationName%>ApplicationTest {

	@Test
	public void contextLoads() {
	}

}
