# J3 — JUnit : tests totalement indépendants

Chaque dossier est un projet Maven autonome avec son propre `pom.xml`, `mvnw.cmd` et `mvnw`.
Il n’existe volontairement aucun `pom.xml` racine.

## Démo TDD

- `01_RED` : lancez `01_premiumCustomerGetsFifteenPercentDiscount.bat` → **échec JUnit attendu**.
- `02_GREEN` : lancez les deux `.bat` l’un après l’autre → verts.
- `03_REFACTOR` : lancez les deux `.bat` l’un après l’autre → verts après refactoring.

## Exemple checkout final

```bat
cd 10_checkout_tdd_final
01_confirmsOrderWhenPaymentIsApproved.bat
02_refusesOrderWhenPaymentIsDeclined.bat
03_sendsConfirmationWhenApproved.bat
04_recordsOrderHistoryWhenApproved.bat
05_appliesPremiumDiscountBeforePayment.bat
```

Chaque commande exécute **une seule méthode JUnit**.

Prérequis : JDK 17+. Maven n’a pas à être installé. Au premier lancement du projet, son wrapper télécharge Maven 3.9.9, puis JUnit 5.11.4.
