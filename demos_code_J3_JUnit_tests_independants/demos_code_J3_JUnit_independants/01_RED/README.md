# 01_RED

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_premiumCustomerGetsFifteenPercentDiscount.bat` → `DiscountServiceTest#premiumCustomerGetsFifteenPercentDiscount` — RED - remise premium : ECHEC JUnit attendu

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=DiscountServiceTest#NomDeLaMethode test`

**Résultat attendu : BUILD FAILURE.** C’est le vrai RED JUnit : attendu 170, obtenu 200.
