@echo off
setlocal
cd /d "%~dp0"
echo === MDE : artefact compilable ===
call mvnw.cmd -Dtest=MiniMdeGeneratorTest#generatedArtifactReallyCompiles test
exit /b %ERRORLEVEL%
