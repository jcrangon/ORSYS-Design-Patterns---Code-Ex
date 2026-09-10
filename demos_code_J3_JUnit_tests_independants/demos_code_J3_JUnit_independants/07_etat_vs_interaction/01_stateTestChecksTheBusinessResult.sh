#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Test d etat ==="
./mvnw "-Dtest=CheckoutTest#stateTestChecksTheBusinessResult" test
