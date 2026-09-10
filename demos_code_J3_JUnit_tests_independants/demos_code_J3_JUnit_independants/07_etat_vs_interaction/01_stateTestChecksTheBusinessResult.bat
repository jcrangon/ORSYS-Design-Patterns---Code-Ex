@echo off
setlocal
cd /d "%~dp0"
echo === Test d etat ===
call mvnw.cmd -Dtest=CheckoutTest#stateTestChecksTheBusinessResult test
exit /b %ERRORLEVEL%
