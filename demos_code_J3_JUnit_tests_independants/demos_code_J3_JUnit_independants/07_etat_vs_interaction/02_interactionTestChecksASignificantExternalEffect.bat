@echo off
setlocal
cd /d "%~dp0"
echo === Test d interaction ===
call mvnw.cmd -Dtest=CheckoutTest#interactionTestChecksASignificantExternalEffect test
exit /b %ERRORLEVEL%
