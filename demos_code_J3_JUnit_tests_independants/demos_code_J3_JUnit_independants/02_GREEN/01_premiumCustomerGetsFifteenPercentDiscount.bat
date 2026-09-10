@echo off
setlocal
cd /d "%~dp0"
echo === GREEN - remise premium ===
call mvnw.cmd -Dtest=DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount test
exit /b %ERRORLEVEL%
