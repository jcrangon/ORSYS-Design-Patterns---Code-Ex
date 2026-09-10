#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== MDE : generation du record ==="
./mvnw "-Dtest=MiniMdeGeneratorTest#generatesAJavaRecordFromTheModel" test
