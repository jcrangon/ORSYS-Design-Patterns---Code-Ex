@echo off
setlocal
cd /d "%~dp0"
echo === assertThrows : montant negatif ===
call mvnw.cmd -Dtest=DiscountRulesTest#rejectsNegativeAmount test
exit /b %ERRORLEVEL%
