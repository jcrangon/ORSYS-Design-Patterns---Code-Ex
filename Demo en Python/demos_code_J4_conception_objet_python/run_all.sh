#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
printf "\n===== 01_factory_et_strategy =====\n"
./01_factory_et_strategy/run.sh
printf "\n===== 02_builder_invariants =====\n"
./02_builder_invariants/run.sh
printf "\n===== 03_adapter_api_externe =====\n"
./03_adapter_api_externe/run.sh
printf "\n===== 04_decorator_proxy_facade =====\n"
./04_decorator_proxy_facade/run.sh
printf "\n===== 05_strategy_et_state =====\n"
./05_strategy_et_state/run.sh
printf "\n===== 06_observer_evenements =====\n"
./06_observer_evenements/run.sh
printf "\n===== 07_command_et_chain =====\n"
./07_command_et_chain/run.sh
printf "\n===== 08_composite_et_visitor =====\n"
./08_composite_et_visitor/run.sh
printf "\n===== 09_checkout_refactoring_final =====\n"
./09_checkout_refactoring_final/run.sh
