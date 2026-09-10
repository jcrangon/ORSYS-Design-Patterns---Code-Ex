# Adapter : isoler une API externe du domaine

**Slides :** 17

## Objectif pédagogique
Comparer les types du fournisseur et le port attendu par le domaine. L’Adapter traduit les données et empêche la propagation des types externes.

## Adaptation Python
Le domaine connaît `Money`, `Receipt` et l’abstraction `PaymentGateway`. `StripeClient` simule le SDK externe. `StripePaymentAdapter` est le seul endroit où les types Stripe sont traduits vers les types du domaine.

## Exécution

### Windows
```bat
run.bat
```

### Linux / macOS
```bash
./run.sh
```

Le script exécute `main.py` avec Python.
