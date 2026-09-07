#!/usr/bin/env bash
set -e
rm -f *.class
javac -encoding UTF-8 CheckoutTddDemo.java
java CheckoutTddDemo
printf '
CI GREEN: compilation + 5 comportements validés.
'
