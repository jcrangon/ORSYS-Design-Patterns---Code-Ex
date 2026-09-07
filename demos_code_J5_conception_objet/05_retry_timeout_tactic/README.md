# Tactique de disponibilité : retry borné sur une dépendance instable

**Slides :** 24, 25

## Objectif pédagogique
Montrer qu'une tactique architecturale répond à un scénario de qualité concret et qu'elle introduit elle-même des coûts : délais, duplication et besoin d'idempotence.

## Exécution

### Windows
```bat
run.bat
```

### Linux / macOS
```bash
./run.sh
```

Le script compile `Main.java` dans `out/` puis exécute `Main`. Aucune dépendance externe ni accès réseau n'est nécessaire.
