# Démo 04 — GRASP : annuler une commande

**Slides : 21 à 29.** Durée : 12 à 15 min.

```bash
python demo_grasp.py
```

- `Order.can_be_cancelled()` et `Order.cancel()` → **Information Expert**.
- `CancelOrderUseCase` → **Controller**.
- `InMemoryOrderRepository` → **Pure Fabrication**.
- `RefundPort` et `NotificationPort` → **Indirection / Protected Variations**.
- Les petits contrats favorisent le **Low Coupling**.

Question : **« Où mettriez-vous la règle : une commande expédiée ne peut plus être annulée ? »** Réponse : dans `Order`, qui possède l’état et protège son invariant.
