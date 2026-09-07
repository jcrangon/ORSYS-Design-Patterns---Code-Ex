# Démos de code — J1 Conception Objet / UML / OCP / LSP

Ce dossier correspond au **prompteur complet du Jour 1** et aux slides J1.
Toutes les démos sont prévues pour **Java 17 ou Java 21**, sans Maven ni dépendance externe.

## Pré-requis

```bash
java --version
javac --version
```

## Ordre recommandé pendant le cours

| Démo | Moment | Slides | Objectif |
|---|---|---:|---|
| 01 — Encapsulation & invariants | Matin, après l’introduction aux invariants | 7 | Montrer qu’un objet protège ses propres règles |
| 02 — OCP & stratégie | Après l’introduction OCP | 23–24 | Passer d’un `switch` sur les variantes à une abstraction extensible |
| 03 — LSP Rectangle/Carré | Après le contrat comportemental | 28 | Faire échouer le même client avec un faux sous-type substituable |
| 04 — Couplage & port métier | Après couplage/cohésion | 31 | Isoler un fournisseur externe derrière un contrat du domaine |

## Exécuter toutes les démos

### Windows

```bat
run_all.bat
```

### Linux / macOS / Git Bash

```bash
chmod +x run_all.sh
./run_all.sh
```

Chaque sous-dossier contient également un `README.md` avec la procédure orale et les commandes à lancer.
