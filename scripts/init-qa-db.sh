#!/bin/bash
# ============================================================
# QA Database Initialization Script (Linux/Mac)
# ============================================================

# Configuration
DB_HOST="qa-db-server"
DB_PORT="3306"
DB_NAME="qa_database"
DB_USER="qa_user"
DB_PASSWORD="${DB_PASSWORD}"
SQL_FILE="sql/qa-init.sql"

echo ""
echo "============================================================"
echo "QA Database Initialization"
echo "============================================================"
echo ""
echo "Host: $DB_HOST"
echo "Port: $DB_PORT"
echo "Database: $DB_NAME"
echo ""

# Check if SQL file exists
if [ ! -f "$SQL_FILE" ]; then
    echo "ERROR: SQL file not found: $SQL_FILE"
    exit 1
fi

# Execute SQL script
echo "Executing SQL script..."
mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASSWORD" "$DB_NAME" < "$SQL_FILE"

if [ $? -eq 0 ]; then
    echo ""
    echo "SUCCESS: Database initialized successfully!"
    echo ""
else
    echo ""
    echo "ERROR: Database initialization failed!"
    echo ""
    exit 1
fi
