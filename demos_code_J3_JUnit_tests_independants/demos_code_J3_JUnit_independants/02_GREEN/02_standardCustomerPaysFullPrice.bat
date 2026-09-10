@echo off
setlocal
cd /d "%~dp0"
echo === GREEN - client standard ===
call mvnw.cmd -Dtest=DiscountServiceTest#standardCustomerPaysFullPrice test
exit /b %ERRORLEVEL%
