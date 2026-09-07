# Démo 01 — DIP : du couplage direct au port

**Slides : 6 à 11.** Durée conseillée : 12 à 15 min.

## 1. Montrer la version avant

```bash
javac DemoDipAvant.java
java DemoDipAvant
```

À faire verbaliser : `CheckoutService` connaît `StripeSdk`, convertit lui-même les euros en centimes et adopte le vocabulaire du fournisseur. Demandez : **« si Stripe change, quelle classe métier faut-il modifier ? »**

## 2. Montrer la version après

```bash
javac DemoDip.java
java DemoDip
```

Points à expliquer :

- `PaymentPort` appartient au **client stable** : le cas d’usage.
- `StripePaymentAdapter` fait la traduction vers le détail technique.
- Le flux d’exécution va toujours vers Stripe, mais la **dépendance du code source** pointe vers l’abstraction.
- Le `fake` de la fin prouve que le cas d’usage peut être exécuté sans SDK externe.

## Modification en direct proposée

Renommez `authorize` en `capture` dans `PaymentPort` et observez les deux seuls endroits réellement impactés : le cas d’usage et son adapter. Faites ensuite remarquer que `StripeSdk` n’a pas contaminé le vocabulaire du coeur.
