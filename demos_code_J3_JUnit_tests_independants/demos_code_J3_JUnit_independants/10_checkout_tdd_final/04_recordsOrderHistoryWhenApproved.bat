@echo off
setlocal
cd /d "%~dp0"
echo === Checkout : historique ===
call mvnw.cmd -Dtest=CheckoutServiceTest#recordsOrderHistoryWhenApproved test
exit /b %ERRORLEVEL%
