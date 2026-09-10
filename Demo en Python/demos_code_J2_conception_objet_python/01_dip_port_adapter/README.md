# Démo 01 — DIP : du couplage direct au port

**Slides : 6 à 11.** Durée conseillée : 12 à 15 min.

## 1. Montrer la version avant

```bash
python demo_dip_avant.py
```

À faire verbaliser : `CheckoutService` connaît `StripeSdk`, convertit lui-même les euros en centimes et adopte le vocabulaire du fournisseur. Demandez : **« si Stripe change, quelle classe métier faut-il modifier ? »**

## 2. Montrer la version après

```bash
python demo_dip.py
```

- `PaymentPort` appartient au **client stable** : le cas d’usage.
- `StripePaymentAdapter` traduit vers le détail technique.
- Le flux d’exécution va vers Stripe, mais la dépendance du code pointe vers l’abstraction.
- `FakePaymentPort` prouve que le cas d’usage fonctionne sans SDK externe.

## Modification en direct proposée

Renommez `authorize()` en `capture()` dans `PaymentPort` et observez les endroits impactés. Faites remarquer que le vocabulaire de `StripeSdk` n’a pas contaminé le coeur.
