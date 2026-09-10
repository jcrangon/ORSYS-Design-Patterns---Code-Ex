# Démos de code Python — J1 Conception Objet / UML / OCP / LSP

Ce dossier est la **transposition Python** du dossier de démos Java du Jour 1.
Il conserve les mêmes scénarios, le même ordre pédagogique et les versions **Avant / Après**.

Les exemples utilisent uniquement la bibliothèque standard Python : **aucune dépendance externe**.

## Pré-requis

Python 3.10+ recommandé :

```bash
python --version
```

Sous certains Windows :

```bat
py --version
```

## Ordre recommandé pendant le cours

| Démo | Moment | Slides | Objectif |
|---|---|---:|---|
| 01 — Encapsulation & invariants | Matin, après l'introduction aux invariants | 7 | Montrer qu'un objet protège ses propres règles |
| 02 — OCP & Strategy | Après l'introduction OCP | 23–24 | Passer d'une condition centrale sur les variantes à une abstraction extensible |
| 03 — LSP Rectangle/Carré | Après le contrat comportemental | 28 | Faire échouer le même client avec un faux sous-type substituable |
| 04 — Couplage & port métier | Après couplage/cohésion | 31 | Isoler un fournisseur externe derrière un contrat du domaine |

## Correspondances Java → Python utilisées dans les démos

| Java | Python dans ce dossier |
|---|---|
| `enum` | `enum.Enum` |
| `record` | `@dataclass(frozen=True)` |
| `interface` | `ABC` + `@abstractmethod` |
| `private` | convention `_attribut` + API publique contrôlée |
| `IllegalArgumentException` | `ValueError` |
| `IllegalStateException` | `RuntimeError` |
| injection par constructeur | injection par `__init__` |
| `extends` | héritage Python |

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
