@echo off
setlocal
cd /d "%~dp0"
echo === REFACTOR - premium conserve ===
call mvnw.cmd -Dtest=DiscountServiceTest#premiumBehaviorIsStillTheSameAfterRefactor test
exit /b %ERRORLEVEL%
