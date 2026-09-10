from dataclasses import dataclass
from collections import defaultdict


@dataclass(frozen=True)
class Dependency:
    source: str
    target: str


FORBIDDEN_FOR_DOMAIN = {"infrastructure", "web", "stripe"}


def assert_rules(dependencies: list[Dependency]) -> None:
    for dependency in dependencies:
        if dependency.source == "domain" and dependency.target in FORBIDDEN_FOR_DOMAIN:
            raise AssertionError(
                f"Règle violée : domain ne doit pas dépendre de {dependency.target}"
            )
    assert_acyclic(dependencies)


def assert_acyclic(dependencies: list[Dependency]) -> None:
    graph: dict[str, list[str]] = defaultdict(list)
    for dependency in dependencies:
        graph[dependency.source].append(dependency.target)

    visiting: set[str] = set()
    visited: set[str] = set()

    def dfs(node: str) -> None:
        if node in visiting:
            raise AssertionError(f"Cycle détecté autour de {node}")
        if node in visited:
            return

        visiting.add(node)
        for next_node in graph.get(node, []):
            dfs(next_node)
        visiting.remove(node)
        visited.add(node)

    for node in list(graph):
        dfs(node)


if __name__ == "__main__":
    valid = [
        Dependency("web", "application"),
        Dependency("application", "domain"),
        Dependency("infrastructure", "application"),
    ]
    assert_rules(valid)
    print("Architecture valide ✓")

    invalid = [
        Dependency("domain", "stripe"),
        Dependency("application", "domain"),
    ]
    try:
        assert_rules(invalid)
    except AssertionError as error:
        print("Architecture refusée ✗ : " + str(error))

    cyclic = [
        Dependency("orders", "billing"),
        Dependency("billing", "orders"),
    ]
    try:
        assert_rules(cyclic)
    except AssertionError as error:
        print("Cycle refusé ✗ : " + str(error))
