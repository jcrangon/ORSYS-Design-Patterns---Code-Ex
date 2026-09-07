# Démonstrations Java — Jour 4

Ces démonstrations accompagnent les slides J4 du cours **Conception objet & Design Patterns**. Elles utilisent uniquement le JDK : **Java 17+**, aucun Maven/Gradle ni accès réseau requis.

## Pré-requis

```bash
java --version
javac --version
```

## Ordre recommandé

1. `01_factory_et_strategy` — **Slides 9–15** — Factory centralise le choix ; Strategy exécute.
2. `02_builder_invariants` — **Slides 12–14** — Builder lisible + invariants dans build().
3. `03_adapter_api_externe` — **Slide 17** — Adapter protège le domaine des types externes.
4. `04_decorator_proxy_facade` — **Slides 20–25** — Comparer enrichissement, contrôle et simplification.
5. `05_strategy_et_state` — **Slides 27–28** — Même forme, intention différente.
6. `06_observer_evenements` — **Slide 29** — Découpler l’émetteur des réactions.
7. `07_command_et_chain` — **Slides 30–32** — Composer contrôles et action réifiée.
8. `08_composite_et_visitor` — **Slides 19 & 34** — Arbre uniforme + opérations extensibles.
9. `09_checkout_refactoring_final` — **Slides 38–45** — Cas final combinant plusieurs patterns.

## Tout exécuter

### Windows
```bat
run_all.bat
```

### Linux / macOS
```bash
./run_all.sh
```

Chaque sous-dossier peut aussi être exécuté indépendamment avec son `run.bat` ou `run.sh`.
