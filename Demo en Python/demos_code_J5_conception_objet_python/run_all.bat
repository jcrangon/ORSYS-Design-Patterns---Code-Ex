@echo off
setlocal
echo ===== 01_repository_port_adapter =====
pushd "%~dp001_repository_port_adapter"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 02_event_bus_idempotence =====
pushd "%~dp002_event_bus_idempotence"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 03_pipeline_validation =====
pushd "%~dp003_pipeline_validation"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 04_plugin_style =====
pushd "%~dp004_plugin_style"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 05_retry_timeout_tactic =====
pushd "%~dp005_retry_timeout_tactic"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 06_observability_correlation =====
pushd "%~dp006_observability_correlation"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 07_architecture_rule =====
pushd "%~dp007_architecture_rule"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 08_spike_performance =====
pushd "%~dp008_spike_performance"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
echo ===== 09_reservation_architecture_finale =====
pushd "%~dp009_reservation_architecture_finale"
call run.bat
if errorlevel 1 exit /b %errorlevel%
popd
echo.
