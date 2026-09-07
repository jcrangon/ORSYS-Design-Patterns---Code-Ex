@echo off
setlocal
echo.
echo ===== 01_tdd_red_green_refactor =====
pushd "%~dp001_tdd_red_green_refactor"
call run.bat || exit /b 1
popd
echo.
echo ===== 02_limites_et_parametres =====
pushd "%~dp002_limites_et_parametres"
call run.bat || exit /b 1
popd
echo.
echo ===== 03_doubles_stub_fake_spy_mock =====
pushd "%~dp003_doubles_stub_fake_spy_mock"
call run.bat || exit /b 1
popd
echo.
echo ===== 04_seams_temps_ids =====
pushd "%~dp004_seams_temps_ids"
call run.bat || exit /b 1
popd
echo.
echo ===== 05_etat_vs_interaction =====
pushd "%~dp005_etat_vs_interaction"
call run.bat || exit /b 1
popd
echo.
echo ===== 06_legacy_characterization_refactor =====
pushd "%~dp006_legacy_characterization_refactor"
call run.bat || exit /b 1
popd
echo.
echo ===== 07_mde_mini_generateur =====
pushd "%~dp007_mde_mini_generateur"
call run.bat || exit /b 1
popd
echo.
echo ===== 08_checkout_tdd_final =====
pushd "%~dp008_checkout_tdd_final"
call run.bat || exit /b 1
popd
echo.
echo Toutes les demonstrations J3 sont OK.
