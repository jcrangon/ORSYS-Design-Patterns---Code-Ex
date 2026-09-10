#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Test parametre : limites 0/99/100/250 ==="
./mvnw "-Dtest=DiscountRulesTest#appliesDiscountAtTheBoundary" test
