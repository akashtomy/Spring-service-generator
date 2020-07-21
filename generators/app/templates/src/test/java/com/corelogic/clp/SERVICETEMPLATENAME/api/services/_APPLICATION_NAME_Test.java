package com.corelogic.clp.<%=packageName%>.api.services;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class <%=applicationName%>Test {
    private <%=applicationName%> service;

    @Before
    public void setUp() {
        service = new <%=applicationName%>();
    }

    @Test
    public void process_whenGivenString_returnsThatString() {
        String result = service.process("StringCheese");

        assertThat(result).isEqualTo("StringCheese");
    }
}
