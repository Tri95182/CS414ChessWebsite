package com.tco.misc;

import com.tco.requests.ConfigRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJSONValidator {

    private void test(String request, Type type, boolean valid) {
        try {
            JSONValidator.validate(request, type);
            assertTrue(valid);
        } catch (Exception e) {
            assertFalse(valid);
        }
    }

    @Test
    @DisplayName("Config request should fail schema validation")
    public void testConfigRequestFail() {
        test("{}", ConfigRequest.class, false);
    }

    @Test
    @DisplayName("Config request should pass schema validation")
    public void testConfigRequestPass() {
        test("{\"requestType\":\"config\",\"features\":[\"config\"]}", ConfigRequest.class, true);
    }

    @Test
    @DisplayName("There should be no schema for the JSONValidator class")
    public void testMissingSchema() {
        test("", JSONValidator.class, false);
    }
}
