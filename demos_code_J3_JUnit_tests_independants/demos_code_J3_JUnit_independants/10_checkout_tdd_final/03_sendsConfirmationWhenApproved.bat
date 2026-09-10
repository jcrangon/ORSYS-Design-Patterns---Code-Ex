@echo off
setlocal
cd /d "%~dp0"
echo === Checkout : notification ===
call mvnw.cmd -Dtest=CheckoutServiceTest#sendsConfirmationWhenApproved test
exit /b %ERRORLEVEL%
