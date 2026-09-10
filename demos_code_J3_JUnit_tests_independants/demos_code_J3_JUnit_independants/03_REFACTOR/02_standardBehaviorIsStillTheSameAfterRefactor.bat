@echo off
setlocal
cd /d "%~dp0"
echo === REFACTOR - standard conserve ===
call mvnw.cmd -Dtest=DiscountServiceTest#standardBehaviorIsStillTheSameAfterRefactor test
exit /b %ERRORLEVEL%
