# Démonstrations de code — Jour 5 — Version Python

Dossier formateur aligné sur les slides J5 du cours **Conception objet & Design Patterns**.

## Prérequis
- Python 3.10 ou supérieur
- Aucune dépendance externe, aucun accès réseau

## Ordre conseillé
- `01_repository_port_adapter` — Repository : isoler le domaine de la persistance
- `02_event_bus_idempotence` — Data Bus : publier un événement et rendre les consumers idempotents
- `03_pipeline_validation` — Pipeline : composer un traitement étape par étape
- `04_plugin_style` — Plug-in Style : étendre le cœur sans le modifier
- `05_retry_timeout_tactic` — Tactique de disponibilité : retry borné sur une dépendance instable
- `06_observability_correlation` — Observabilité : corrélation, métriques et preuve de fonctionnement
- `07_architecture_rule` — Règle d’architecture automatisable : détecter une dépendance interdite
- `08_spike_performance` — Spike technique : tester une hypothèse avant une décision coûteuse
- `09_reservation_architecture_finale` — Cas fil rouge : architecture testable avec repository, payment adapter et event bus

## Tout exécuter
### Windows
```bat
run_all.bat
```

### Linux/macOS
```bash
./run_all.sh
```

Chaque démo possède aussi son propre `README.md`, `run.bat` et `run.sh`.

## Adaptation Java → Python
Les interfaces Java sont représentées par des `Protocol` ou des classes abstraites selon le besoin. Les `record` deviennent des `@dataclass(frozen=True)`. Les lambdas et fonctions Python permettent de garder les exemples de pipeline et d'Event Bus très lisibles.
