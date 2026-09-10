@echo off
setlocal
cd /d "%~dp0"
if "%~1"=="" (
  echo Usage: run_one_test.bat NomDeLaMethode
  exit /b 2
)
call mvnw.cmd -Dtest=MiniMdeGeneratorTest#%~1 test
exit /b %ERRORLEVEL%
