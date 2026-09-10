# Démo 01 — vrai RED / GREEN / REFACTOR avec pytest

## 1. RED
```bash
cd red
python -m pytest -q
```
Le test doit échouer : attendu 170, obtenu 200.

## 2. GREEN
```bash
cd ../green
python -m pytest -q
```
On ajoute uniquement le code nécessaire pour obtenir 170 pour un client premium.

## 3. REFACTOR
```bash
cd ../refactor
python -m pytest -q
```
Les tests restent verts pendant que la logique de remise devient une `DiscountPolicy`.

À dire : **RED** prouve que le comportement manque ; **GREEN** prouve qu'il existe ; **REFACTOR** améliore la structure sans changer le comportement.
