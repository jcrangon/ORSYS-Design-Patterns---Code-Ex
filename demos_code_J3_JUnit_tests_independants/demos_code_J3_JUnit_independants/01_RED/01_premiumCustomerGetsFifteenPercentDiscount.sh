#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== RED - remise premium : ECHEC JUnit attendu ==="
./mvnw "-Dtest=DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount" test
