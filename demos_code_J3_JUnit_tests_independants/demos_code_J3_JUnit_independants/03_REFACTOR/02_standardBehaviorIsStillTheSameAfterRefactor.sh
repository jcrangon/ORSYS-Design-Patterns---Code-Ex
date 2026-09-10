#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== REFACTOR - standard conserve ==="
./mvnw "-Dtest=DiscountServiceTest#standardBehaviorIsStillTheSameAfterRefactor" test
