package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class HealthController {

    @Value("${app.environment:unknown}")
    private String environment;

    /**
     * Health Check Endpoint
     */
    @GetMapping("/api/health")
    public HealthResponse health() {
        return new HealthResponse("UP", environment);
    }

    /**
     * Information Endpoint
     */
    @GetMapping("/api/info")
    public InfoResponse info() {
        return new InfoResponse(
            "Java Project",
            "1.0.0",
            environment,
            System.getProperty("java.version")
        );
    }

    /**
     * Ready Probe for Kubernetes
     */
    @GetMapping("/ready")
    public HealthResponse ready() {
        return new HealthResponse("READY", environment);
    }

    /**
     * Liveness Probe for Kubernetes
     */
    @GetMapping("/live")
    public HealthResponse live() {
        return new HealthResponse("LIVE", environment);
    }

    /**
     * Health Response DTO
     */
    public static class HealthResponse {
        public String status;
        public String environment;
        public long timestamp;

        public HealthResponse(String status, String environment) {
            this.status = status;
            this.environment = environment;
            this.timestamp = System.currentTimeMillis();
        }

        public String getStatus() { return status; }
        public String getEnvironment() { return environment; }
        public long getTimestamp() { return timestamp; }
    }

    /**
     * Info Response DTO
     */
    public static class InfoResponse {
        public String applicationName;
        public String version;
        public String environment;
        public String javaVersion;

        public InfoResponse(String applicationName, String version, String environment, String javaVersion) {
            this.applicationName = applicationName;
            this.version = version;
            this.environment = environment;
            this.javaVersion = javaVersion;
        }

        public String getApplicationName() { return applicationName; }
        public String getVersion() { return version; }
        public String getEnvironment() { return environment; }
        public String getJavaVersion() { return javaVersion; }
    }
}
