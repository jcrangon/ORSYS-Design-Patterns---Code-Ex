@echo off
setlocal
cd /d "%~dp0"
echo === Characterization test du legacy ===
call mvnw.cmd -Dtest=ShippingCharacterizationTest#characterizesCurrentLegacyBehavior test
exit /b %ERRORLEVEL%
