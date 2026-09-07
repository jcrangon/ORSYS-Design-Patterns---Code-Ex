# Démonstrations de code — Jour 3

Cours **Conception objet & Design Patterns — J3 : TDD, tests, doubles, MDE et refactorisation**.

Toutes les démos sont volontairement exécutables avec **Java 17+ uniquement**, sans Maven, Gradle, JUnit ni Mockito. Le prompteur montre en parallèle la syntaxe JUnit/Mockito des slides, puis fait exécuter ici des équivalents autonomes. Le but est de pouvoir enseigner même sans accès Internet ni cache de dépendances.

## Vérification de l'environnement

```bash
java --version
javac --version
```

Sous Windows, si les accents s'affichent mal :

```bat
chcp 65001
```

## Ordre des démonstrations

| # | Dossier | Thème | Slides |
|---|---|---|---|
| 01 | `01_tdd_red_green_refactor` | cycle Red / Green / Refactor | 13–16 |
| 02 | `02_limites_et_parametres` | partitions, bornes, tests paramétrés | 18–20 |
| 03 | `03_doubles_stub_fake_spy_mock` | stub, fake, spy, mock | 22–24 |
| 04 | `04_seams_temps_ids` | seams, horloge, identifiants déterministes | 25–27 |
| 05 | `05_etat_vs_interaction` | assertions d’état vs interactions | 29–30 |
| 06 | `06_legacy_characterization_refactor` | characterization tests + refactoring | 33–35 |
| 07 | `07_mde_mini_generateur` | modèle → transformation → artefact | 37–39 |
| 08 | `08_checkout_tdd_final` | TP fil rouge : ports, fakes, spy, suite CI | 41–47 |

## Exécuter toutes les démos

Linux/macOS :
```bash
chmod +x run_all.sh
./run_all.sh
```

Windows :
```bat
run_all.bat
```

Chaque démo possède son propre `README.md`, `run.sh` et `run.bat`.
