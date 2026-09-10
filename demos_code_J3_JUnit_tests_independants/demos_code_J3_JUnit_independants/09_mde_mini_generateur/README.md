# 09_mde_mini_generateur

Projet Maven/JUnit **autonome** : aucun parent Maven, aucun autre dossier requis.

## Lancer un seul test sous Windows

- `01_generatesAJavaRecordFromTheModel.bat` → `MiniMdeGeneratorTest#generatesAJavaRecordFromTheModel` — MDE : generation du record
- `02_generatedArtifactReallyCompiles.bat` → `MiniMdeGeneratorTest#generatedArtifactReallyCompiles` — MDE : artefact compilable
- `03_rejectsInvalidModel.bat` → `MiniMdeGeneratorTest#rejectsInvalidModel` — MDE : modele invalide refuse

## Tous les tests de ce projet uniquement

- Windows : `run_all_tests.bat`
- Linux/macOS : `./run_all_tests.sh`

Commande directe : `mvnw.cmd -Dtest=MiniMdeGeneratorTest#NomDeLaMethode test`
