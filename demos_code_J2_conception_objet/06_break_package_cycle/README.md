# Démo 06 — Casser une dépendance cyclique

**Slides : 39–40.** Durée : 12 min.

## Avant : orders ↔ billing

```bash
mkdir -p avant/out
javac -d avant/out avant/src/com/acme/orders/OrderService.java avant/src/com/acme/billing/BillingService.java avant/src/com/acme/app/Main.java
java -cp avant/out com.acme.app.Main
```

Dessinez au tableau : `orders → billing → orders`. Faites remarquer le `setOrders()` nécessaire après construction : ce n’est pas la définition d’un cycle, mais c’est un **symptôme très parlant**.

## Après : dépendance vers un contrat stable

```bash
mkdir -p apres/out
javac -d apres/out apres/src/com/acme/contracts/RefundListener.java apres/src/com/acme/billing/BillingService.java apres/src/com/acme/orders/OrderRefundListener.java apres/src/com/acme/orders/OrderService.java apres/src/com/acme/app/Main.java
java -cp apres/out com.acme.app.Main
```

Le cycle est cassé grâce à `RefundListener`. Expliquez qu’un vrai projet pourrait aussi utiliser un événement de domaine, une orchestration applicative, fusionner deux packages trop intimement liés, ou déplacer le contrat vers le côté stable. **Ne pas déplacer arbitrairement toutes les classes dans `common`.**
