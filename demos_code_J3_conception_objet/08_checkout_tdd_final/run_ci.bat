@echo off
del /q *.class 2>nul
javac -encoding UTF-8 CheckoutTddDemo.java || exit /b 1
java CheckoutTddDemo || exit /b 1
echo.
echo CI GREEN: compilation + 5 comportements valides.
