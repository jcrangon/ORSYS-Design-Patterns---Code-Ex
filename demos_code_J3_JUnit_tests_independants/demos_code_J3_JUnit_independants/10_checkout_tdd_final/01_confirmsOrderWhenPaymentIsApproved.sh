#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Checkout : paiement accepte ==="
./mvnw "-Dtest=CheckoutServiceTest#confirmsOrderWhenPaymentIsApproved" test
