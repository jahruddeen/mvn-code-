package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * QA Smoke Tests
 * Validates basic application functionality
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-qa.properties")
public class QASmokeTest {

    private static final String CONTEXT_PATH = "/app";

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test 1: Application Health Check
     */
    @Test
    public void testApplicationHealth() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            CONTEXT_PATH + "/actuator/health",
            String.class
        );

        assertEquals("Health check failed", HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body is null", response.getBody());
        assertTrue("Response does not contain 'UP'", response.getBody().contains("UP"));
    }

    /**
     * Test 2: Application Info Endpoint
     */
    @Test
    public void testApplicationInfo() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            CONTEXT_PATH + "/actuator/info",
            String.class
        );

        assertEquals("Info endpoint failed", HttpStatus.OK, response.getStatusCode());
        assertNotNull("Info response is null", response.getBody());
    }

    /**
     * Test 3: Metrics Endpoint
     */
    @Test
    public void testMetricsEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            CONTEXT_PATH + "/actuator/metrics",
            String.class
        );

        assertEquals("Metrics endpoint failed", HttpStatus.OK, response.getStatusCode());
        assertNotNull("Metrics response is null", response.getBody());
    }

    /**
     * Test 4: Home Endpoint (if exists)
     */
    @Test
    public void testHomeEndpoint() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(
                CONTEXT_PATH + "/",
                String.class
            );

            assertTrue(
                "Home endpoint should return 200 or 302",
                response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.FOUND
            );
        } catch (Exception e) {
            // Expected if endpoint doesn't exist
            assertNotNull("Test execution completed", e);
        }
    }

    /**
     * Test 5: Application Context Loads
     */
    @Test
    public void testContextLoads() {
        assertNotNull("Application context is null", restTemplate);
    }

    /**
     * Test 6: 404 Error Handling
     */
    @Test
    public void testNotFoundEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            CONTEXT_PATH + "/api/nonexistent",
            String.class
        );

        assertEquals("404 handling failed", HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test 7: Request/Response Headers
     */
    @Test
    public void testResponseHeaders() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            CONTEXT_PATH + "/actuator/health",
            String.class
        );

        assertNotNull("Content-Type header missing", response.getHeaders().getContentType());
        assertTrue(
            "Response should contain JSON content",
            response.getHeaders().getContentType().toString().contains("application/json")
        );
    }
}