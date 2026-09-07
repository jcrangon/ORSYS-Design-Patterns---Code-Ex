#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
echo "\n===== 01_factory_et_strategy ====="
./01_factory_et_strategy/run.sh
echo "\n===== 02_builder_invariants ====="
./02_builder_invariants/run.sh
echo "\n===== 03_adapter_api_externe ====="
./03_adapter_api_externe/run.sh
echo "\n===== 04_decorator_proxy_facade ====="
./04_decorator_proxy_facade/run.sh
echo "\n===== 05_strategy_et_state ====="
./05_strategy_et_state/run.sh
echo "\n===== 06_observer_evenements ====="
./06_observer_evenements/run.sh
echo "\n===== 07_command_et_chain ====="
./07_command_et_chain/run.sh
echo "\n===== 08_composite_et_visitor ====="
./08_composite_et_visitor/run.sh
echo "\n===== 09_checkout_refactoring_final ====="
./09_checkout_refactoring_final/run.sh
