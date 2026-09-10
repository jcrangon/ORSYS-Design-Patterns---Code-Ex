#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Checkout : historique ==="
./mvnw "-Dtest=CheckoutServiceTest#recordsOrderHistoryWhenApproved" test
