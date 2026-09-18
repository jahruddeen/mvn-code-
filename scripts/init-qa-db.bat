@echo off
REM ============================================================
REM QA Database Initialization Script
REM ============================================================

setlocal enabledelayedexpansion

REM Configuration
set DB_HOST=qa-db-server
set DB_PORT=3306
set DB_NAME=qa_database
set DB_USER=qa_user
set DB_PASSWORD=%DB_PASSWORD%
set SQL_FILE=sql\qa-init.sql

echo.
echo ============================================================
echo QA Database Initialization
echo ============================================================
echo.
echo Host: %DB_HOST%
echo Port: %DB_PORT%
echo Database: %DB_NAME%
echo.

REM Check if SQL file exists
if not exist "%SQL_FILE%" (
    echo ERROR: SQL file not found: %SQL_FILE%
    exit /b 1
)

REM Execute SQL script
echo Executing SQL script...
mysql -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < %SQL_FILE%

if %errorlevel% equ 0 (
    echo.
    echo SUCCESS: Database initialized successfully!
    echo.
) else (
    echo.
    echo ERROR: Database initialization failed!
    echo.
    exit /b 1
)

endlocal
