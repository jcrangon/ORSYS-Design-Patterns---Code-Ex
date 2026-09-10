#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Stub + Fake + Spy + Mock manuel ==="
./mvnw "-Dtest=CheckoutServiceTest#demonstratesStubFakeSpyAndManualMock" test
