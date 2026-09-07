# Démo 01 — Encapsulation et invariants

**Moment :** après la slide 7.

## 1. Montrer le problème

```bash
javac DemoEncapsulationAvant.java
java DemoEncapsulationAvant
```

À expliquer : `public` ne crée aucune barrière métier. Le programme accepte un prix négatif et une commande expédiée sans paiement.

## 2. Montrer l’objet qui protège ses invariants

```bash
javac DemoEncapsulation.java
java DemoEncapsulation
```

Sortie attendue :

```text
Commande{total=2000 cents, statut=BROUILLON}
Commande{total=2000 cents, statut=EXPEDIEE}
```

## 3. Déclencher volontairement une violation

Décommenter dans `main` :

```java
c.ajouterLigne(500);
```

Recompiler et relancer. Une `IllegalStateException: Commande non modifiable` doit apparaître.

**Message oral :** l’exception est levée au plus près de la règle. Tous les appelants bénéficient donc du même garde-fou.
