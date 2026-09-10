# 02_GREEN

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_premiumCustomerGetsFifteenPercentDiscount.bat` → `DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount` — GREEN - remise premium
- `02_standardCustomerPaysFullPrice.bat` → `DiscountServiceTest#standardCustomerPaysFullPrice` — GREEN - client standard

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=DiscountServiceTest#NomDeLaMethode test`
