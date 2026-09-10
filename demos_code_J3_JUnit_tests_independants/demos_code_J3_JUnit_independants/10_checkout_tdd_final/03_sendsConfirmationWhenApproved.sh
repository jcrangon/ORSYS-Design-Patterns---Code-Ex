#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Checkout : notification ==="
./mvnw "-Dtest=CheckoutServiceTest#sendsConfirmationWhenApproved" test
