package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * QA Integration Tests
 * Tests interaction between multiple components
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-qa.properties")
public class QAIntegrationTest {

    @Autowired
    private Environment environment;

    /**
     * Test 1: Application Initialization
     */
    @Test
    public void testApplicationInitialization() {
        assertNotNull("Application context failed to initialize", environment);
    }

    /**
     * Test 2: Configuration Properties Loaded
     */
    @Test
    public void testPropertiesLoaded() {
        String applicationName = environment.getProperty("spring.application.name");
        String appEnvironment = environment.getProperty("app.environment");

        assertNotNull(
            "Spring application name property not loaded",
            applicationName
        );

        assertEquals(
            "QA environment property not loaded correctly",
            "qa",
            appEnvironment
        );
    }

    /**
     * Test 3: Logging Configuration
     */
    @Test
    public void testLoggingConfiguration() {
        String loggingLevel = environment.getProperty("logging.level.com.example");

        assertNotNull(
            "Logging configuration not loaded",
            loggingLevel
        );

        assertEquals(
            "Logging level should be DEBUG",
            "DEBUG",
            loggingLevel
        );
    }
}