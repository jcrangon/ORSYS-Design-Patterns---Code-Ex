@echo off
setlocal
cd /d "%~dp0"
echo === Seam : horloge controlable ===
call mvnw.cmd -Dtest=SeamsTest#clockCanBeControlled test
exit /b %ERRORLEVEL%
