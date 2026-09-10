# 08_legacy_characterization_refactor

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_characterizesCurrentLegacyBehavior.bat` → `ShippingCharacterizationTest#characterizesCurrentLegacyBehavior` — Characterization test du legacy
- `02_refactoredVersionPreservesCharacterizedBehavior.bat` → `ShippingCharacterizationTest#refactoredVersionPreservesCharacterizedBehavior` — Refactor : comportement preserve

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=ShippingCharacterizationTest#NomDeLaMethode test`
