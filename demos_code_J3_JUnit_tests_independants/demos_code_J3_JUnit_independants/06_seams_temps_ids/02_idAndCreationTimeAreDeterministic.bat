@echo off
setlocal
cd /d "%~dp0"
echo === Seams : id et date deterministes ===
call mvnw.cmd -Dtest=SeamsTest#idAndCreationTimeAreDeterministic test
exit /b %ERRORLEVEL%
