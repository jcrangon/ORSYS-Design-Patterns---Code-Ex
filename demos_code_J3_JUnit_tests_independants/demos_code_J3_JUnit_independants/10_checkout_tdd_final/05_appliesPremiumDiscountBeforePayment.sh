#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Checkout : remise premium ==="
./mvnw "-Dtest=CheckoutServiceTest#appliesPremiumDiscountBeforePayment" test
