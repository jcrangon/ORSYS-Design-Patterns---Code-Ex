#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== GREEN - client standard ==="
./mvnw "-Dtest=DiscountServiceTest#standardCustomerPaysFullPrice" test
