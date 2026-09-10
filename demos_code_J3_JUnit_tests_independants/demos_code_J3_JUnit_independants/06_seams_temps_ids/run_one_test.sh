#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
[ "$#" -ge 1 ] || { echo "Usage: ./run_one_test.sh NomDeLaMethode"; exit 2; }
./mvnw "-Dtest=SeamsTest#$1" test
