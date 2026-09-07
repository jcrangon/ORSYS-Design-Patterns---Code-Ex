#!/usr/bin/env bash
set -e
ROOT="$(cd "$(dirname "$0")" && pwd)"
echo "\n===== 01_tdd_red_green_refactor ====="
(cd "$ROOT/01_tdd_red_green_refactor" && chmod +x *.sh && ./run.sh)
echo "\n===== 02_limites_et_parametres ====="
(cd "$ROOT/02_limites_et_parametres" && chmod +x *.sh && ./run.sh)
echo "\n===== 03_doubles_stub_fake_spy_mock ====="
(cd "$ROOT/03_doubles_stub_fake_spy_mock" && chmod +x *.sh && ./run.sh)
echo "\n===== 04_seams_temps_ids ====="
(cd "$ROOT/04_seams_temps_ids" && chmod +x *.sh && ./run.sh)
echo "\n===== 05_etat_vs_interaction ====="
(cd "$ROOT/05_etat_vs_interaction" && chmod +x *.sh && ./run.sh)
echo "\n===== 06_legacy_characterization_refactor ====="
(cd "$ROOT/06_legacy_characterization_refactor" && chmod +x *.sh && ./run.sh)
echo "\n===== 07_mde_mini_generateur ====="
(cd "$ROOT/07_mde_mini_generateur" && chmod +x *.sh && ./run.sh)
echo "\n===== 08_checkout_tdd_final ====="
(cd "$ROOT/08_checkout_tdd_final" && chmod +x *.sh && ./run.sh)
echo "\nToutes les démonstrations J3 sont OK."
