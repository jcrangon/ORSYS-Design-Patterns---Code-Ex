@echo off
setlocal
cd /d "%~dp0"
echo === Test parametre : limites 0/99/100/250 ===
call mvnw.cmd -Dtest=DiscountRulesTest#appliesDiscountAtTheBoundary test
exit /b %ERRORLEVEL%
