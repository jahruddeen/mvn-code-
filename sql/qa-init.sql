-- ============================================================
-- QA Database Setup Script
-- ============================================================
-- This script initializes the QA database with required tables

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Application Logs Table
CREATE TABLE IF NOT EXISTS application_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    level VARCHAR(10) NOT NULL,
    logger VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Audit Trail Table
CREATE TABLE IF NOT EXISTS audit_trail (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    action VARCHAR(50) NOT NULL,
    entity_type VARCHAR(100) NOT NULL,
    entity_id BIGINT NOT NULL,
    user_id BIGINT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details JSON
);

-- Create Indexes
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_logs_level ON application_logs(level);
CREATE INDEX idx_audit_action ON audit_trail(action);

-- Insert Test Data
INSERT INTO users (username, email) VALUES 
    ('test_user', 'test@example.com'),
    ('qa_user', 'qa@example.com'),
    ('admin_user', 'admin@example.com');

-- Display Summary
SELECT 'Database initialization completed successfully!' AS status;
