# Démo 07 — Métriques de stabilité Ca / Ce / I

**Slide : 41.** Durée : 7 à 8 min.

```bash
javac DemoStability.java
java DemoStability
```

Rappels :

- `Ca` (afferent coupling) : combien de packages **dépendent de celui-ci**.
- `Ce` (efferent coupling) : de combien de packages **celui-ci dépend**.
- `I = Ce / (Ca + Ce)`.
- `I ≈ 0` : très stable ; `I ≈ 1` : très instable.

Ne présentez surtout pas la métrique comme une note de qualité. Un adapter Stripe peut être volontairement instable. Le problème est plutôt un package **très stable** qui dépend d’un détail **très instable**.

Modifiez en direct les valeurs de `shared` et demandez aux stagiaires pourquoi un package énormément consommé devient coûteux à faire évoluer.
