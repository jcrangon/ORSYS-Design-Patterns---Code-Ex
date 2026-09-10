#!/usr/bin/env bash
set -e
ROOT="$(cd "$(dirname "$0")" && pwd)"
echo "===== 01_repository_port_adapter ====="
(cd "$ROOT/01_repository_port_adapter" && ./run.sh)
echo
echo "===== 02_event_bus_idempotence ====="
(cd "$ROOT/02_event_bus_idempotence" && ./run.sh)
echo
echo "===== 03_pipeline_validation ====="
(cd "$ROOT/03_pipeline_validation" && ./run.sh)
echo
echo "===== 04_plugin_style ====="
(cd "$ROOT/04_plugin_style" && ./run.sh)
echo
echo "===== 05_retry_timeout_tactic ====="
(cd "$ROOT/05_retry_timeout_tactic" && ./run.sh)
echo
echo "===== 06_observability_correlation ====="
(cd "$ROOT/06_observability_correlation" && ./run.sh)
echo
echo "===== 07_architecture_rule ====="
(cd "$ROOT/07_architecture_rule" && ./run.sh)
echo
echo "===== 08_spike_performance ====="
(cd "$ROOT/08_spike_performance" && ./run.sh)
echo
echo "===== 09_reservation_architecture_finale ====="
(cd "$ROOT/09_reservation_architecture_finale" && ./run.sh)
echo
