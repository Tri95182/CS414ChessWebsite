package com.tco.requests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRegisterRequest {

    private RegisterRequest reg;

    @BeforeEach
    public void createConfigurationForTestCases() {
        reg = new RegisterRequest();
        reg.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"register\"")
    public void testType() {
        String type = reg.getRequestType();
        assertEquals("register", type);
    }

    @Test
    @DisplayName("Register result is successful")
    public void testSuccess() {
        assertTrue(reg.success);
    }

}