# 07_etat_vs_interaction

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_stateTestChecksTheBusinessResult.bat` → `CheckoutTest#stateTestChecksTheBusinessResult` — Test d etat
- `02_interactionTestChecksASignificantExternalEffect.bat` → `CheckoutTest#interactionTestChecksASignificantExternalEffect` — Test d interaction

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=CheckoutTest#NomDeLaMethode test`
