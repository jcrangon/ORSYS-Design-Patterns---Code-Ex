@echo off
setlocal
cd /d "%~dp0"
echo === Checkout : remise premium ===
call mvnw.cmd -Dtest=CheckoutServiceTest#appliesPremiumDiscountBeforePayment test
exit /b %ERRORLEVEL%
