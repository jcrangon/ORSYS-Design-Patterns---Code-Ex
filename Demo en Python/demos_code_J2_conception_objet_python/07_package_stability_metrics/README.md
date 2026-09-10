# Démo 07 — Métriques de stabilité Ca / Ce / I

**Slide : 41.** Durée : 7 à 8 min.

```bash
python demo_stability.py
```

- `Ca` : combien de packages **dépendent de celui-ci**.
- `Ce` : de combien de packages **celui-ci dépend**.
- `I = Ce / (Ca + Ce)`.
- `I ≈ 0` : très stable ; `I ≈ 1` : très instable.

Ce n’est pas une note de qualité. Un adapter Stripe peut être volontairement instable. Modifiez les valeurs de `shared` et demandez pourquoi un package énormément consommé devient coûteux à faire évoluer.
