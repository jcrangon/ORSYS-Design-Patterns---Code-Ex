# Démos de code — J2 Conception Objet / DIP / ISP / GRASP / Packages

Ce dossier accompagne les **slides J2 — Classes, dépendances et packages** et le support enrichi J2.
Toutes les démos sont prévues pour **Java 17 ou Java 21**, sans Maven ni dépendance externe.

## Pré-requis

```bash
java --version
javac --version
```

## Ordre recommandé pendant le cours

| Démo | Moment | Slides | Objectif |
|---|---|---:|---|
| 01 — DIP : couplage direct → port | Après slides 6 à 11 | 6–11 | Inverser une dépendance vers Stripe et faire appartenir le contrat au cas d’usage |
| 02 — Injection : constructeur vs Service Locator | Après slide 12 | 12–13 | Rendre les dépendances explicites, remplaçables et testables |
| 03 — ISP : interface grasse → rôles | Après slides 16 à 20 | 16–20 | Séparer les contrats selon les vrais clients |
| 04 — GRASP : annuler une commande | Après slides 21 à 29 | 21–29 | Distribuer Expert, Controller, Pure Fabrication et Indirection |
| 05 — API publique d’un package | Après slides 30 à 33 | 30–33 | Montrer qu’un package possède une façade publique et des détails internes |
| 06 — Casser une dépendance cyclique | Après slides 39–40 | 39–40 | Passer d’un cycle orders↔billing à un contrat stable |
| 07 — Stabilité Ca/Ce/I | Après slide 41 | 41 | Calculer et interpréter l’instabilité d’un package |
| 08 — Règles d’architecture exécutables | Après slide 43 | 43 | Faire échouer automatiquement un graphe de dépendances interdit |

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

Chaque sous-dossier contient un `README.md` expliquant **quoi montrer, quoi dire, quoi modifier en direct et quelles commandes lancer**.

> Astuce formateur : compilez une première fois avant l’arrivée des stagiaires. Pendant le cours, effacez les dossiers `out/` et relancez les commandes pour matérialiser le cycle complet édition → compilation → exécution.
