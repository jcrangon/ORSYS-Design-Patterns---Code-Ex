# Démos Jour 3 — version réellement JUnit

Cette version remplace les anciens `Checks.equals`, `Checks.isTrue` et la mini `TestSuite` artisanale par **JUnit Jupiter**.

## Prérequis
- JDK 17 ou plus récent
- Maven 3.9+ recommandé

Vérification :
```bash
java --version
mvn --version
```

Au premier lancement, Maven télécharge JUnit Jupiter depuis Maven Central.

## Lancer toutes les démos vertes
À la racine :
```bash
mvn test
```
La phase RED n'est volontairement pas incluse dans ce lancement global puisqu'elle doit échouer.

## Démo 01 : vraie boucle TDD
### RED — échec attendu
```bash
cd 01_tdd_red_green_refactor/red
mvn test
```
Vous devez voir un échec JUnit : **expected: <170> but was: <200>**.

### GREEN
```bash
cd ../green
mvn test
```
Les deux tests passent.

### REFACTOR
```bash
cd ../refactor
mvn test
```
Les comportements restent verts avec une structure plus propre.

## Ordre des démos
1. `01_tdd_red_green_refactor` — RED / GREEN / REFACTOR
2. `02_limites_et_parametres` — `@ParameterizedTest`, `@CsvSource`, `assertThrows`
3. `03_doubles_stub_fake_spy_mock` — doubles manuels + assertions JUnit
4. `04_seams_temps_ids` — horloge et IDs déterministes
5. `05_etat_vs_interaction` — état vs effet externe
6. `06_legacy_characterization_refactor` — characterization tests
7. `07_mde_mini_generateur` — `@TempDir`, génération et compilation d'un artefact
8. `08_checkout_tdd_final` — suite finale avec `@BeforeEach`

## Commandes IntelliJ IDEA
Ouvrir le `pom.xml` racine comme projet Maven. Les fichiers sous `src/test/java` sont reconnus comme tests JUnit et peuvent être lancés avec le bouton vert à gauche de `@Test`.

## Message pédagogique
Les tests ne sont plus simulés. C'est JUnit qui :
- découvre les méthodes annotées `@Test` ;
- exécute les cas paramétrés ;
- lève et affiche les échecs d'assertion ;
- produit le rapport de tests.
