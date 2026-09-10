#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Seam : horloge controlable ==="
./mvnw "-Dtest=SeamsTest#clockCanBeControlled" test
