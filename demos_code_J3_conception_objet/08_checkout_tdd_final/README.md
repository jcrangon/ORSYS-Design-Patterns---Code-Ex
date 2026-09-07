# Démo 08 — TP fil rouge Checkout piloté par tests

**Slides associées :** 41 à 47

## Objectif pédagogique
Faire apparaître les responsabilités et ports à partir d’une test list proche de celle de la slide 42.

## Fichiers à ouvrir
`CheckoutTddDemo.java`, `run_ci.sh`, `run_ci.bat`

## Exécution
```bash
./run_ci.sh
```

## À faire verbaliser
Relier chaque test à un choix de conception : PaymentPort pour contrôler le paiement, fake repository pour la persistance, spy notifier pour l’effet externe, DiscountPolicy pour la variation métier. Le script final joue le rôle d’un mini pipeline CI.
