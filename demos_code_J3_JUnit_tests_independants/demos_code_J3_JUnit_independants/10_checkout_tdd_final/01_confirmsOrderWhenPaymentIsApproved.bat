@echo off
setlocal
cd /d "%~dp0"
echo === Checkout : paiement accepte ===
call mvnw.cmd -Dtest=CheckoutServiceTest#confirmsOrderWhenPaymentIsApproved test
exit /b %ERRORLEVEL%
