# Démo 03 — LSP : Rectangle / Carré mutable

**Moment :** slide 28.

## Démonstration de la violation

```bash
javac DemoLsp.java
java DemoLsp
```

Sortie :

```text
Rectangle : OK
Exception in thread "main" java.lang.AssertionError: Le client attendait 50 mais obtient 25
```

Le point à commenter n’est pas « un carré n’est pas un rectangle en mathématiques ». Le point est : **le contrat mutable de `Rectangle` promet que largeur et hauteur peuvent être changées indépendamment**. `Carre` ne peut pas tenir cette promesse.

## Une correction possible

```bash
javac DemoLspCorrige.java
java DemoLspCorrige
```

Ici, `Rectangle` et `Carre` partagent seulement la capacité réellement commune : `aire()`.

**Message oral :** LSP est comportemental. `extends` et la compilation ne suffisent pas à garantir la substituabilité.
