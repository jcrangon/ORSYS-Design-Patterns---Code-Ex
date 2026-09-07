#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
echo "\n===== 01_repository_port_adapter ====="
./01_repository_port_adapter/run.sh
echo "\n===== 02_event_bus_idempotence ====="
./02_event_bus_idempotence/run.sh
echo "\n===== 03_pipeline_validation ====="
./03_pipeline_validation/run.sh
echo "\n===== 04_plugin_style ====="
./04_plugin_style/run.sh
echo "\n===== 05_retry_timeout_tactic ====="
./05_retry_timeout_tactic/run.sh
echo "\n===== 06_observability_correlation ====="
./06_observability_correlation/run.sh
echo "\n===== 07_architecture_rule ====="
./07_architecture_rule/run.sh
echo "\n===== 08_spike_performance ====="
./08_spike_performance/run.sh
echo "\n===== 09_reservation_architecture_finale ====="
./09_reservation_architecture_finale/run.sh
