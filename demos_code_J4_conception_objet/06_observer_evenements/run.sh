#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
rm -rf out && mkdir out
javac -encoding UTF-8 -d out Main.java
java -cp out Main
