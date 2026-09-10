#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== assertThrows : montant negatif ==="
./mvnw "-Dtest=DiscountRulesTest#rejectsNegativeAmount" test
