# Démo 03 — ISP : séparer selon les clients

**Slides : 16 à 20.** Durée : 10 à 12 min.

### Avant

```bash
javac DemoIspAvant.java
java DemoIspAvant
```

Montrez le `UnsupportedOperationException`. Expliquez que l’objet est forcé de promettre des capacités qu’il ne possède pas.

### Après

```bash
javac DemoIsp.java
java DemoIsp
```

Faites observer que `PaymentScreen` ne voit que `PaymentReader`, alors que `RefundUseCase` voit seulement ce dont il a besoin. L’implémentation concrète peut parfaitement implémenter plusieurs rôles.

À marteler : **ISP ne signifie pas « une méthode par interface »**. Le bon découpage suit les raisons de changer et les consommateurs réels.
