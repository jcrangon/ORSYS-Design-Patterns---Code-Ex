#!/usr/bin/env bash
set -e
javac -encoding UTF-8 RedPhase.java && java RedPhase
javac -encoding UTF-8 GreenPhase.java && java GreenPhase
javac -encoding UTF-8 RefactorPhase.java && java RefactorPhase
