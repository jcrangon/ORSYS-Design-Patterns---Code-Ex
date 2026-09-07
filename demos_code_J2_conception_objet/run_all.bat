@echo off
setlocal
cd /d %~dp0

echo === J2 / 01 DIP ===
pushd 01_dip_port_adapter
javac DemoDipAvant.java DemoDip.java || exit /b 1
java DemoDipAvant || exit /b 1
java DemoDip || exit /b 1
popd

echo === J2 / 02 Injection ===
pushd 02_injection_constructor_vs_locator
javac DemoInjection.java || exit /b 1
java DemoInjection || exit /b 1
popd

echo === J2 / 03 ISP ===
pushd 03_isp_roles
javac DemoIspAvant.java DemoIsp.java || exit /b 1
java DemoIspAvant || exit /b 1
java DemoIsp || exit /b 1
popd

echo === J2 / 04 GRASP ===
pushd 04_grasp_cancel_order
javac DemoGrasp.java || exit /b 1
java DemoGrasp || exit /b 1
popd

echo === J2 / 05 Packages ===
pushd 05_package_api_visibility
if exist out rmdir /s /q out
mkdir out
javac -d out src\com\acme\orders\internal\OrderRepository.java src\com\acme\orders\api\OrderService.java src\com\acme\app\Main.java || exit /b 1
java -cp out com.acme.app.Main || exit /b 1
popd

echo === J2 / 06 Cycle avant/apres ===
pushd 06_break_package_cycle
if exist avant\out rmdir /s /q avant\out
if exist apres\out rmdir /s /q apres\out
mkdir avant\out
mkdir apres\out
javac -d avant\out avant\src\com\acme\orders\OrderService.java avant\src\com\acme\billing\BillingService.java avant\src\com\acme\app\Main.java || exit /b 1
java -cp avant\out com.acme.app.Main || exit /b 1
javac -d apres\out apres\src\com\acme\contracts\RefundListener.java apres\src\com\acme\billing\BillingService.java apres\src\com\acme\orders\OrderRefundListener.java apres\src\com\acme\orders\OrderService.java apres\src\com\acme\app\Main.java || exit /b 1
java -cp apres\out com.acme.app.Main || exit /b 1
popd

echo === J2 / 07 Stabilite ===
pushd 07_package_stability_metrics
javac DemoStability.java || exit /b 1
java DemoStability || exit /b 1
popd

echo === J2 / 08 Regles architecture ===
pushd 08_executable_architecture_rules
javac DemoArchitectureRules.java || exit /b 1
java DemoArchitectureRules || exit /b 1
popd

echo === Toutes les demos J2 ont reussi ===
endlocal
