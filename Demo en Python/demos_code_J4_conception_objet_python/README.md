# Démonstrations Python — Jour 4

Ces démonstrations sont la conversion Python des démos Java du J4 du cours **Conception objet & Design Patterns**.

Elles conservent les mêmes objectifs, le même ordre et les mêmes patterns, avec une écriture adaptée à Python.

## Pré-requis

- Python **3.10+** recommandé
- aucune bibliothèque externe

Vérification :

```bash
python --version
```

## Ordre recommandé

1. `01_factory_et_strategy` — **Slides 9–15** — Factory centralise le choix ; Strategy exécute.
2. `02_builder_invariants` — **Slides 12–14** — Builder lisible + invariants dans `build()`.
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

## Choix de traduction Java → Python

- interfaces Java → `ABC` / `@abstractmethod` lorsque cela clarifie le pattern ;
- `record` Java → `@dataclass(frozen=True)` ;
- `Consumer` / `Runnable` → callables Python ;
- `switch` des factories → dictionnaire de classes ;
- composition et injection conservées telles quelles ;
- aucune dépendance externe afin que les étudiants puissent lancer immédiatement les exemples.
