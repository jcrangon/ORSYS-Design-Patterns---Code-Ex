@echo off
setlocal
cd /d "%~dp0"
echo === Refactor : comportement preserve ===
call mvnw.cmd -Dtest=ShippingCharacterizationTest#refactoredVersionPreservesCharacterizedBehavior test
exit /b %ERRORLEVEL%
