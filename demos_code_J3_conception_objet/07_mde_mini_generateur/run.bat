@echo off
if exist generated rmdir /s /q generated
javac -encoding UTF-8 MiniMdeGenerator.java || exit /b 1
java MiniMdeGenerator || exit /b 1
javac -encoding UTF-8 GeneratedArtifactTest.java generated\Order.java || exit /b 1
java GeneratedArtifactTest || exit /b 1
