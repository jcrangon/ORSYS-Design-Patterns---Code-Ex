@echo off
setlocal
set ROOT=%~dp0

echo ============================================================
echo 01 - Encapsulation et invariants
echo ============================================================
cd /d "%ROOT%01_encapsulation_invariants"
del /q *.class 2>nul
javac DemoEncapsulationAvant.java && java DemoEncapsulationAvant
javac DemoEncapsulation.java && java DemoEncapsulation

echo.
echo ============================================================
echo 02 - OCP et strategie
echo ============================================================
cd /d "%ROOT%02_ocp_strategie"
del /q *.class 2>nul
javac DemoOcpAvant.java && java DemoOcpAvant
javac DemoOcp.java && java DemoOcp

echo.
echo ============================================================
echo 03 - LSP Rectangle / Carre
echo ============================================================
cd /d "%ROOT%03_lsp_rectangle_carre"
del /q *.class 2>nul
javac DemoLsp.java
java DemoLsp
rem L'AssertionError est volontaire : continuer avec la correction.
javac DemoLspCorrige.java && java DemoLspCorrige

echo.
echo ============================================================
echo 04 - Couplage paiement
echo ============================================================
cd /d "%ROOT%04_couplage_paiement"
del /q *.class 2>nul
javac DemoCouplageAvant.java && java DemoCouplageAvant
javac DemoCouplage.java && java DemoCouplage

echo.
echo Fin des demos.
pause
