# Démo 04 — Couplage à un fournisseur externe

**Moment :** slide 31. Démo optionnelle si le temps le permet.

Aucune dépendance Stripe réelle n’est nécessaire : le SDK est simulé pour garder la démo instantanée et hors réseau.

## Avant : le cas d’usage connaît le fournisseur

```bash
javac DemoCouplageAvant.java
java DemoCouplageAvant
```

Le cas d’usage connaît `StripeClient`, `StripeResponse` et la chaîne `"paid"`. Le vocabulaire technique fuit dans le métier.

## Après : contrat métier + adaptateur

```bash
javac DemoCouplage.java
java DemoCouplage
```

À montrer :

```java
interface PaiementPort {
    boolean payer(int cents);
}
```

Puis :

```java
ValiderCommande(PaiementPort paiement) {
    this.paiement = paiement;
}
```

**Message oral :** le cas d’usage dépend de la capacité dont il a besoin. L’adaptateur dépend du fournisseur. Le fournisseur n’impose plus son modèle au cœur métier.

Le `FakePaiement` permet aussi de faire le lien avec la testabilité qui sera approfondie plus tard.
