#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== Seams : id et date deterministes ==="
./mvnw "-Dtest=SeamsTest#idAndCreationTimeAreDeterministic" test
