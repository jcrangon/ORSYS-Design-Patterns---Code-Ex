#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== REFACTOR - premium conserve ==="
./mvnw "-Dtest=DiscountServiceTest#premiumBehaviorIsStillTheSameAfterRefactor" test
