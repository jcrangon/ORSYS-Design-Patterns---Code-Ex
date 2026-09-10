#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
echo "=== MDE : modele invalide refuse ==="
./mvnw "-Dtest=MiniMdeGeneratorTest#rejectsInvalidModel" test
