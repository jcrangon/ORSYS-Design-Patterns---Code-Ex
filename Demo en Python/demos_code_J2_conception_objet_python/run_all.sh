#!/usr/bin/env bash
set -e
ROOT="$(cd "$(dirname "$0")" && pwd)"
PYTHON_BIN="${PYTHON_BIN:-python3}"
command -v "$PYTHON_BIN" >/dev/null 2>&1 || PYTHON_BIN=python

echo "=== J2 / 01 DIP ==="
(cd "$ROOT/01_dip_port_adapter" && "$PYTHON_BIN" demo_dip_avant.py && "$PYTHON_BIN" demo_dip.py)

echo "=== J2 / 02 Injection ==="
(cd "$ROOT/02_injection_constructor_vs_locator" && "$PYTHON_BIN" demo_injection.py)

echo "=== J2 / 03 ISP ==="
(cd "$ROOT/03_isp_roles" && "$PYTHON_BIN" demo_isp_avant.py && "$PYTHON_BIN" demo_isp.py)

echo "=== J2 / 04 GRASP ==="
(cd "$ROOT/04_grasp_cancel_order" && "$PYTHON_BIN" demo_grasp.py)

echo "=== J2 / 05 Packages ==="
(cd "$ROOT/05_package_api_visibility" && PYTHONPATH=src "$PYTHON_BIN" -m com.acme.app.main)

echo "=== J2 / 06 Cycle avant ==="
(cd "$ROOT/06_break_package_cycle" && PYTHONPATH=avant/src "$PYTHON_BIN" -m com.acme.app.main)

echo "=== J2 / 06 Cycle après ==="
(cd "$ROOT/06_break_package_cycle" && PYTHONPATH=apres/src "$PYTHON_BIN" -m com.acme.app.main)

echo "=== J2 / 07 Stabilité ==="
(cd "$ROOT/07_package_stability_metrics" && "$PYTHON_BIN" demo_stability.py)

echo "=== J2 / 08 Règles architecture ==="
(cd "$ROOT/08_executable_architecture_rules" && "$PYTHON_BIN" demo_architecture_rules.py)

echo "=== Toutes les démos J2 Python ont réussi ==="
