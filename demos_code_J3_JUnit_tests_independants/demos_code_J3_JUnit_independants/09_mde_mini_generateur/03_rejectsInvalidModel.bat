@echo off
setlocal
cd /d "%~dp0"
echo === MDE : modele invalide refuse ===
call mvnw.cmd -Dtest=MiniMdeGeneratorTest#rejectsInvalidModel test
exit /b %ERRORLEVEL%
