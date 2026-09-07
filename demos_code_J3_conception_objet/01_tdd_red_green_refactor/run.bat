@echo off
setlocal
javac -encoding UTF-8 RedPhase.java && java RedPhase || exit /b 1
javac -encoding UTF-8 GreenPhase.java && java GreenPhase || exit /b 1
javac -encoding UTF-8 RefactorPhase.java && java RefactorPhase || exit /b 1
