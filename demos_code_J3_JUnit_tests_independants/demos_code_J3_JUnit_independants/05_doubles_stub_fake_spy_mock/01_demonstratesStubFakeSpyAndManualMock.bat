@echo off
setlocal
cd /d "%~dp0"
echo === Stub + Fake + Spy + Mock manuel ===
call mvnw.cmd -Dtest=CheckoutServiceTest#demonstratesStubFakeSpyAndManualMock test
exit /b %ERRORLEVEL%
