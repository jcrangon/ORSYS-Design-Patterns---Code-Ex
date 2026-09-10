#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Characterization test du legacy ==="
./mvnw "-Dtest=ShippingCharacterizationTest#characterizesCurrentLegacyBehavior" test
