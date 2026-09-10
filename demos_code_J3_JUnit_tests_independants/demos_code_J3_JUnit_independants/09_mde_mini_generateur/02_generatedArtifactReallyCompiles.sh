#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== MDE : artefact compilable ==="
./mvnw "-Dtest=MiniMdeGeneratorTest#generatedArtifactReallyCompiles" test
