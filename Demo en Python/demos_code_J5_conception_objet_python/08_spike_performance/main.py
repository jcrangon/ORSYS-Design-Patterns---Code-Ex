
from time import perf_counter_ns


def search_linear(data: list[int], target: int) -> int:
    start = perf_counter_ns()
    for value in data:
        if value == target:
            break
    return perf_counter_ns() - start


def search_set(data: set[int], target: int) -> int:
    start = perf_counter_ns()
    target in data
    return perf_counter_ns() - start


def main() -> None:
    n = 500_000
    values = list(range(n))
    values_set = set(values)
    target = n - 1

    # Échauffement
    for _ in range(5):
        search_linear(values, target)
        search_set(values_set, target)

    linear_total = 0
    set_total = 0
    for _ in range(20):
        linear_total += search_linear(values, target)
        set_total += search_set(values_set, target)

    print(f"Moyenne liste : {linear_total / 20 / 1_000_000:.3f} ms")
    print(f"Moyenne set   : {set_total / 20 / 1_000_000:.6f} ms")
    print("À commenter : le spike donne une preuve locale ; il ne remplace pas un benchmark réaliste du système complet.")


if __name__ == "__main__":
    main()
