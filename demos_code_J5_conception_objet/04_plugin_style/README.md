# Plug-in Style : étendre le cœur sans le modifier

**Slides :** 20, 22

## Objectif pédagogique
Faire voir l'OCP à l'échelle d'une application : le cœur ne connaît que l'interface du plug-in, les extensions s'enregistrent à l'extérieur.

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
