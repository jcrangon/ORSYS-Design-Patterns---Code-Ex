@echo off
setlocal
cd /d %~dp0
echo.
echo ===== 01_repository_port_adapter =====
call 01_repository_port_adapter\run.bat || exit /b 1
echo.
echo ===== 02_event_bus_idempotence =====
call 02_event_bus_idempotence\run.bat || exit /b 1
echo.
echo ===== 03_pipeline_validation =====
call 03_pipeline_validation\run.bat || exit /b 1
echo.
echo ===== 04_plugin_style =====
call 04_plugin_style\run.bat || exit /b 1
echo.
echo ===== 05_retry_timeout_tactic =====
call 05_retry_timeout_tactic\run.bat || exit /b 1
echo.
echo ===== 06_observability_correlation =====
call 06_observability_correlation\run.bat || exit /b 1
echo.
echo ===== 07_architecture_rule =====
call 07_architecture_rule\run.bat || exit /b 1
echo.
echo ===== 08_spike_performance =====
call 08_spike_performance\run.bat || exit /b 1
echo.
echo ===== 09_reservation_architecture_finale =====
call 09_reservation_architecture_finale\run.bat || exit /b 1
