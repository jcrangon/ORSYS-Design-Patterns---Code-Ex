@echo off
setlocal
set ROOT=%~dp0

where py >nul 2>nul
if %errorlevel%==0 (
    set PYTHON=py -3
) else (
    set PYTHON=python
)

echo ============================================================
echo 01 - Encapsulation et invariants
echo ============================================================
cd /d "%ROOT%01_encapsulation_invariants"
%PYTHON% DemoEncapsulationAvant.py
%PYTHON% DemoEncapsulation.py

echo.
echo ============================================================
echo 02 - OCP et strategie
echo ============================================================
cd /d "%ROOT%02_ocp_strategie"
%PYTHON% DemoOcpAvant.py
%PYTHON% DemoOcp.py

echo.
echo ============================================================
echo 03 - LSP Rectangle / Carre
echo ============================================================
cd /d "%ROOT%03_lsp_rectangle_carre"
%PYTHON% DemoLsp.py
rem L'AssertionError est volontaire : continuer avec la correction.
%PYTHON% DemoLspCorrige.py

echo.
echo ============================================================
echo 04 - Couplage paiement
echo ============================================================
cd /d "%ROOT%04_couplage_paiement"
%PYTHON% DemoCouplageAvant.py
%PYTHON% DemoCouplage.py

echo.
echo Fin des demos.
pause
