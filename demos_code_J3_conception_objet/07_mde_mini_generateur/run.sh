#!/usr/bin/env bash
set -e
rm -rf generated *.class
javac -encoding UTF-8 MiniMdeGenerator.java
java MiniMdeGenerator
javac -encoding UTF-8 GeneratedArtifactTest.java generated/Order.java
java GeneratedArtifactTest
