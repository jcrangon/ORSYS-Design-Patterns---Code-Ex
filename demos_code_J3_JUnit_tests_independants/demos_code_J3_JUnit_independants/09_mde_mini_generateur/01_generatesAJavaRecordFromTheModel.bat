@echo off
setlocal
cd /d "%~dp0"
echo === MDE : generation du record ===
call mvnw.cmd -Dtest=MiniMdeGeneratorTest#generatesAJavaRecordFromTheModel test
exit /b %ERRORLEVEL%
