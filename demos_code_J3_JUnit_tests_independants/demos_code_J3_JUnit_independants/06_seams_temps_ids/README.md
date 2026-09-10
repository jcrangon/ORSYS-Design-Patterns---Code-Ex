# 06_seams_temps_ids

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_clockCanBeControlled.bat` → `SeamsTest#clockCanBeControlled` — Seam : horloge controlable
- `02_idAndCreationTimeAreDeterministic.bat` → `SeamsTest#idAndCreationTimeAreDeterministic` — Seams : id et date deterministes

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=SeamsTest#NomDeLaMethode test`
