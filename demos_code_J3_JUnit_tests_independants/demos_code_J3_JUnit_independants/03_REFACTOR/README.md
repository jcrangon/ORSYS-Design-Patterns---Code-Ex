# 03_REFACTOR

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_premiumBehaviorIsStillTheSameAfterRefactor.bat` → `DiscountServiceTest#premiumBehaviorIsStillTheSameAfterRefactor` — REFACTOR - premium conserve
- `02_standardBehaviorIsStillTheSameAfterRefactor.bat` → `DiscountServiceTest#standardBehaviorIsStillTheSameAfterRefactor` — REFACTOR - standard conserve

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=DiscountServiceTest#NomDeLaMethode test`
