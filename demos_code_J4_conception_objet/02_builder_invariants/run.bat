@echo off
setlocal
cd /d %~dp0
if exist out rmdir /s /q out
mkdir out
javac -encoding UTF-8 -d out Main.java || exit /b 1
java -cp out Main
