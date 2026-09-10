#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== GREEN - remise premium ==="
./mvnw "-Dtest=DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount" test
