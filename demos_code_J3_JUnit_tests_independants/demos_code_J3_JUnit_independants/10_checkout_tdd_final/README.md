# 10_checkout_tdd_final

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_confirmsOrderWhenPaymentIsApproved.bat` → `CheckoutServiceTest#confirmsOrderWhenPaymentIsApproved` — Checkout : paiement accepte
- `02_refusesOrderWhenPaymentIsDeclined.bat` → `CheckoutServiceTest#refusesOrderWhenPaymentIsDeclined` — Checkout : paiement refuse
- `03_sendsConfirmationWhenApproved.bat` → `CheckoutServiceTest#sendsConfirmationWhenApproved` — Checkout : notification
- `04_recordsOrderHistoryWhenApproved.bat` → `CheckoutServiceTest#recordsOrderHistoryWhenApproved` — Checkout : historique
- `05_appliesPremiumDiscountBeforePayment.bat` → `CheckoutServiceTest#appliesPremiumDiscountBeforePayment` — Checkout : remise premium

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=CheckoutServiceTest#NomDeLaMethode test`
