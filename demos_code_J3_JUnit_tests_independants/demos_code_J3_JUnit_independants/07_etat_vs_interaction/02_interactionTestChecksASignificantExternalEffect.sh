#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Test d interaction ==="
./mvnw "-Dtest=CheckoutTest#interactionTestChecksASignificantExternalEffect" test
