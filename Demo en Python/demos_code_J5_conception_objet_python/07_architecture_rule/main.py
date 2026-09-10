
from dataclasses import dataclass


@dataclass(frozen=True)
class Dependency:
    from_package: str
    to_package: str


def violations(dependencies: list[Dependency]) -> list[str]:
    result: list[str] = []
    for dependency in dependencies:
        if (
            dependency.from_package.startswith("app.domain")
            and dependency.to_package.startswith("app.infrastructure")
        ):
            result.append(f"{dependency.from_package} -> {dependency.to_package}")
    return result


def main() -> None:
    graph = [
        Dependency("app.web", "app.application"),
        Dependency("app.application", "app.domain"),
        Dependency("app.infrastructure.sql", "app.domain"),
        Dependency("app.domain.booking", "app.infrastructure.sql"),
    ]

    bad = violations(graph)
    print("Violations =", bad)
    if bad:
        print("CI: FAILED - le domaine dépend de l'infrastructure")

    print("À commenter : une règle documentée seulement dans un wiki se dégrade silencieusement.")


if __name__ == "__main__":
    main()
