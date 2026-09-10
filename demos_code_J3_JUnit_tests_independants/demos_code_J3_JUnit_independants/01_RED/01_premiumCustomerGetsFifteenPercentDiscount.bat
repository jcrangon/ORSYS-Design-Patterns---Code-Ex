@echo off
setlocal
cd /d "%~dp0"
echo === RED - remise premium : ECHEC JUnit attendu ===
call mvnw.cmd -Dtest=DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount test
exit /b %ERRORLEVEL%
