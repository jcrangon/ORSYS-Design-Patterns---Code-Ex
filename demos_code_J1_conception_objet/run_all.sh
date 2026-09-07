#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"

run_demo() {
  local dir="$1"
  shift
  echo
  echo "============================================================"
  echo "$dir"
  echo "============================================================"
  cd "$ROOT/$dir"
  rm -f ./*.class
  for main in "$@"; do
    echo
    echo ">>> javac $main.java"
    javac "$main.java"
    echo ">>> java $main"
    if [[ "$main" == "DemoLsp" ]]; then
      java "$main" || true
    else
      java "$main"
    fi
  done
}

run_demo 01_encapsulation_invariants DemoEncapsulationAvant DemoEncapsulation
run_demo 02_ocp_strategie DemoOcpAvant DemoOcp
run_demo 03_lsp_rectangle_carre DemoLsp DemoLspCorrige
run_demo 04_couplage_paiement DemoCouplageAvant DemoCouplage

echo
echo "Toutes les démos ont été lancées."
