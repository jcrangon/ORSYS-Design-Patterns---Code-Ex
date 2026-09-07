#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT"

echo "=== J2 / 01 DIP ==="
(cd 01_dip_port_adapter && rm -f *.class && javac DemoDipAvant.java DemoDip.java && java DemoDipAvant && java DemoDip)

echo "=== J2 / 02 Injection ==="
(cd 02_injection_constructor_vs_locator && rm -f *.class && javac DemoInjection.java && java DemoInjection)

echo "=== J2 / 03 ISP ==="
(cd 03_isp_roles && rm -f *.class && javac DemoIspAvant.java DemoIsp.java && java DemoIspAvant && java DemoIsp)

echo "=== J2 / 04 GRASP ==="
(cd 04_grasp_cancel_order && rm -f *.class && javac DemoGrasp.java && java DemoGrasp)

echo "=== J2 / 05 Packages ==="
(cd 05_package_api_visibility && rm -rf out && mkdir out && javac -d out src/com/acme/orders/internal/OrderRepository.java src/com/acme/orders/api/OrderService.java src/com/acme/app/Main.java && java -cp out com.acme.app.Main)

echo "=== J2 / 06 Cycle avant ==="
(cd 06_break_package_cycle && rm -rf avant/out apres/out && mkdir -p avant/out apres/out && javac -d avant/out avant/src/com/acme/orders/OrderService.java avant/src/com/acme/billing/BillingService.java avant/src/com/acme/app/Main.java && java -cp avant/out com.acme.app.Main)

echo "=== J2 / 06 Cycle après ==="
(cd 06_break_package_cycle && javac -d apres/out apres/src/com/acme/contracts/RefundListener.java apres/src/com/acme/billing/BillingService.java apres/src/com/acme/orders/OrderRefundListener.java apres/src/com/acme/orders/OrderService.java apres/src/com/acme/app/Main.java && java -cp apres/out com.acme.app.Main)

echo "=== J2 / 07 Stabilité ==="
(cd 07_package_stability_metrics && rm -f *.class && javac DemoStability.java && java DemoStability)

echo "=== J2 / 08 Règles architecture ==="
(cd 08_executable_architecture_rules && rm -f *.class && javac DemoArchitectureRules.java && java DemoArchitectureRules)

echo "=== Toutes les démos J2 ont réussi ==="
