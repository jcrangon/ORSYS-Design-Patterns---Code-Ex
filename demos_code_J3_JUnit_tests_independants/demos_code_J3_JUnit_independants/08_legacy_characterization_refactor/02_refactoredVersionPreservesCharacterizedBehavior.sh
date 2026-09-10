#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Refactor : comportement preserve ==="
./mvnw "-Dtest=ShippingCharacterizationTest#refactoredVersionPreservesCharacterizedBehavior" test
