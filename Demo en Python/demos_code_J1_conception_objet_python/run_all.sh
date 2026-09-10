#!/usr/bin/env bash
set -u
ROOT="$(cd "$(dirname "$0")" && pwd)"
PYTHON_BIN="${PYTHON_BIN:-python3}"

run_demo() {
  local dir="$1"
  shift
  echo
  echo "============================================================"
  echo "$dir"
  echo "============================================================"
  cd "$ROOT/$dir"
  for main in "$@"; do
    echo
    echo ">>> $PYTHON_BIN $main.py"
    if [[ "$main" == "DemoLsp" ]]; then
      "$PYTHON_BIN" "$main.py" || true
    else
      "$PYTHON_BIN" "$main.py"
    fi
  done
}

run_demo 01_encapsulation_invariants DemoEncapsulationAvant DemoEncapsulation
run_demo 02_ocp_strategie DemoOcpAvant DemoOcp
run_demo 03_lsp_rectangle_carre DemoLsp DemoLspCorrige
run_demo 04_couplage_paiement DemoCouplageAvant DemoCouplage

echo
echo "Toutes les démos ont été lancées."
