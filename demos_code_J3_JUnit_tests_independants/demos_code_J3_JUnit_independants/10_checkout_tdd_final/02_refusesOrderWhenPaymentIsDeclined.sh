#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Checkout : paiement refuse ==="
./mvnw "-Dtest=CheckoutServiceTest#refusesOrderWhenPaymentIsDeclined" test
