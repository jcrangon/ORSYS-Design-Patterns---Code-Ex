@echo off
setlocal
cd /d "%~dp0"
echo === Checkout : paiement refuse ===
call mvnw.cmd -Dtest=CheckoutServiceTest#refusesOrderWhenPaymentIsDeclined test
exit /b %ERRORLEVEL%
