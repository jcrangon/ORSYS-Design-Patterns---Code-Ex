# Démo 05 — API publique d’un package

**Slides : 30 à 33.** Durée : 8 à 10 min.

Depuis ce dossier :

```bash
mkdir -p out
javac -d out src/com/acme/orders/internal/OrderRepository.java src/com/acme/orders/api/OrderService.java src/com/acme/app/Main.java
java -cp out com.acme.app.Main
```

Expliquez la séparation : `com.acme.orders.api` représente l’API consommable ; `com.acme.orders.internal` contient les choix techniques. En Java classique, un sous-package n’est **pas** automatiquement caché par le langage, d’où l’intérêt des conventions, des modules ou de règles d’architecture automatisées.

### Manipulation pédagogique

Décommentez l’import de `OrderRepository` dans `Main.java` et dites : **« Ça compile éventuellement, mais est-ce pour autant une bonne dépendance ? »** C’est le bon moment pour distinguer **possible techniquement** et **autorisé architecturalement**.
