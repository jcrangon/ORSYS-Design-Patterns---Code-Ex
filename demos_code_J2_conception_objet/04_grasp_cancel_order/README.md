# Démo 04 — GRASP : annuler une commande

**Slides : 21 à 29.** Durée : 12 à 15 min.

```bash
javac DemoGrasp.java
java DemoGrasp
```

Utilisez cette démo comme **revue de responsabilités** :

- `Order.canBeCancelled()` et `Order.cancel()` → **Information Expert**.
- `CancelOrderUseCase` → **Controller** : orchestre le scénario.
- `InMemoryOrderRepository` → **Pure Fabrication** : responsabilité technique extraite du domaine.
- `RefundPort` et `NotificationPort` → **Indirection / Protected Variations**.
- Les petits contrats évitent de coupler le cas d’usage à un SDK concret → **Low Coupling**.

Question à poser : **« Où mettriez-vous la règle : une commande expédiée ne peut plus être annulée ? »** La réponse doit revenir vers l’objet `Order`, parce qu’il possède l’état et doit protéger son invariant.
