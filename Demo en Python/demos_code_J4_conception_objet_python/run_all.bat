@echo off
setlocal
cd /d %~dp0
echo.
echo ===== 01_factory_et_strategy =====
call 01_factory_et_strategy\run.bat || exit /b 1
echo.
echo ===== 02_builder_invariants =====
call 02_builder_invariants\run.bat || exit /b 1
echo.
echo ===== 03_adapter_api_externe =====
call 03_adapter_api_externe\run.bat || exit /b 1
echo.
echo ===== 04_decorator_proxy_facade =====
call 04_decorator_proxy_facade\run.bat || exit /b 1
echo.
echo ===== 05_strategy_et_state =====
call 05_strategy_et_state\run.bat || exit /b 1
echo.
echo ===== 06_observer_evenements =====
call 06_observer_evenements\run.bat || exit /b 1
echo.
echo ===== 07_command_et_chain =====
call 07_command_et_chain\run.bat || exit /b 1
echo.
echo ===== 08_composite_et_visitor =====
call 08_composite_et_visitor\run.bat || exit /b 1
echo.
echo ===== 09_checkout_refactoring_final =====
call 09_checkout_refactoring_final\run.bat || exit /b 1
