# Démo 02 — OCP : du switch à une stratégie

**Moment :** slides 23–24.

## Version avant

```bash
javac DemoOcpAvant.java
java DemoOcpAvant
```

Faire remarquer que la liste des variantes est connue par la fonction centrale. Ajouter `DRONE` oblige à rouvrir l’énumération et le `switch`.

## Version après

```bash
javac DemoOcp.java
java DemoOcp
```

La classe `Drone` est déjà présente pour la démonstration. Montrez-la, puis revenez à :

```java
static int totalAvecLivraison(Commande c, CalculFraisLivraison calcul) {
    return c.totalCents() + calcul.calculer(c);
}
```

**Question à poser :** « Pour ajouter Drone, quelles lignes de cette méthode ai-je modifiées ? »

Réponse : aucune.

**Point important :** le `switch` n’est pas mauvais en soi. Le refactoring devient intéressant quand l’axe de variation est réel, répété et coûteux à rouvrir.
