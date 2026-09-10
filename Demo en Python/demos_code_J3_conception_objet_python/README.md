# Démos Jour 3 — Python / pytest

Cette version reprend les mêmes objectifs pédagogiques que les démos Java/JUnit, en Python avec **pytest**.

## Prérequis
- Python 3.11+ recommandé
- pytest

Installation :
```bash
python -m pip install -r requirements.txt
```

## Lancer toutes les démos vertes
À la racine :
```bash
python -m pytest -q
```
La phase **RED** de la démo 01 est volontairement exclue du lancement global car elle doit échouer.

## Démo 01 : vraie boucle TDD
### RED — échec attendu
```bash
cd 01_tdd_red_green_refactor/red
python -m pytest -q
```
Attendu : le test échoue car 170 est attendu mais 200 est obtenu.

### GREEN
```bash
cd ../green
python -m pytest -q
```
Les tests passent.

### REFACTOR
```bash
cd ../refactor
python -m pytest -q
```
Les comportements restent identiques avec une structure plus propre.

## Ordre des démos
1. `01_tdd_red_green_refactor` — RED / GREEN / REFACTOR
2. `02_limites_et_parametres` — `@pytest.mark.parametrize` et `pytest.raises`
3. `03_doubles_stub_fake_spy_mock` — Stub, Fake, Spy et Mock manuel
4. `04_seams_temps_ids` — horloge et IDs déterministes
5. `05_etat_vs_interaction` — test d'état vs test d'interaction
6. `06_legacy_characterization_refactor` — characterization tests
7. `07_mde_mini_generateur` — génération et compilation d'un artefact Python
8. `08_checkout_tdd_final` — suite finale avec fixture pytest

## Message pédagogique
pytest joue ici le même rôle que JUnit dans les démos originales :
- découverte automatique des tests ;
- tests paramétrés ;
- vérification d'exceptions ;
- fixtures ;
- affichage détaillé des échecs.
