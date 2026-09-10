# Démos de code — J2 Conception Objet / DIP / ISP / GRASP / Packages — version Python

Conversion Python des démos Java du J2. Les objectifs pédagogiques, l’ordre et les scénarios « avant / après » sont conservés.

## Pré-requis

Python **3.10+** recommandé.

```bash
python --version
```

| Démo | Slides | Objectif |
|---|---:|---|
| 01 — DIP : couplage direct → port | 6–11 | Inverser la dépendance vers Stripe |
| 02 — Injection : constructeur vs Service Locator | 12–13 | Rendre les dépendances explicites et testables |
| 03 — ISP : interface grasse → rôles | 16–20 | Séparer les contrats selon les clients |
| 04 — GRASP : annuler une commande | 21–29 | Expert, Controller, Pure Fabrication, Indirection |
| 05 — API publique d’un package | 30–33 | Exposer une API et signaler les détails internes |
| 06 — Casser un cycle | 39–40 | Passer de orders↔billing à un contrat stable |
| 07 — Stabilité Ca/Ce/I | 41 | Calculer l’instabilité d’un package |
| 08 — Règles d’architecture | 43 | Refuser automatiquement dépendances et cycles interdits |

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

Chaque sous-dossier contient un `README.md` avec les commandes et les points à expliquer pendant le cours.

> Note : Python applique moins de barrières de visibilité que Java. Dans les démos de packages, `_internal` exprime donc une **convention architecturale**, que l’on peut renforcer par des outils automatisés.
