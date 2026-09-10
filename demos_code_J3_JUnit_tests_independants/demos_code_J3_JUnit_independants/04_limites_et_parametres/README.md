# 04_limites_et_parametres

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_appliesDiscountAtTheBoundary.bat` → `DiscountRulesTest#appliesDiscountAtTheBoundary` — Test parametre : limites 0/99/100/250
- `02_rejectsNegativeAmount.bat` → `DiscountRulesTest#rejectsNegativeAmount` — assertThrows : montant negatif

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=DiscountRulesTest#NomDeLaMethode test`
