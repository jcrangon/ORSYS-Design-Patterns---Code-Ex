@echo off
setlocal
cd /d %~dp0
py -3 main.py 2>nul || python main.py
