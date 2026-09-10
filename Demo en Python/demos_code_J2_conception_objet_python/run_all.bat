@echo off
setlocal
set ROOT=%~dp0

echo === J2 / 01 DIP ===
pushd "%ROOT%01_dip_port_adapter"
python demo_dip_avant.py || exit /b 1
python demo_dip.py || exit /b 1
popd

echo === J2 / 02 Injection ===
pushd "%ROOT%02_injection_constructor_vs_locator"
python demo_injection.py || exit /b 1
popd

echo === J2 / 03 ISP ===
pushd "%ROOT%03_isp_roles"
python demo_isp_avant.py || exit /b 1
python demo_isp.py || exit /b 1
popd

echo === J2 / 04 GRASP ===
pushd "%ROOT%04_grasp_cancel_order"
python demo_grasp.py || exit /b 1
popd

echo === J2 / 05 Packages ===
pushd "%ROOT%05_package_api_visibility"
set PYTHONPATH=src
python -m com.acme.app.main || exit /b 1
popd

echo === J2 / 06 Cycle avant ===
pushd "%ROOT%06_break_package_cycle"
set PYTHONPATH=avant/src
python -m com.acme.app.main || exit /b 1
popd

echo === J2 / 06 Cycle apres ===
pushd "%ROOT%06_break_package_cycle"
set PYTHONPATH=apres/src
python -m com.acme.app.main || exit /b 1
popd

echo === J2 / 07 Stabilite ===
pushd "%ROOT%07_package_stability_metrics"
set PYTHONPATH=
python demo_stability.py || exit /b 1
popd

echo === J2 / 08 Regles architecture ===
pushd "%ROOT%08_executable_architecture_rules"
python demo_architecture_rules.py || exit /b 1
popd

echo === Toutes les demos J2 Python ont reussi ===
endlocal
