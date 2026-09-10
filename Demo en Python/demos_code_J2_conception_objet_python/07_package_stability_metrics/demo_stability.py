from dataclasses import dataclass


@dataclass(frozen=True)
class PackageStats:
    name: str
    ca: int
    ce: int

    def instability(self) -> float:
        total = self.ca + self.ce
        return 0.0 if total == 0 else self.ce / total


if __name__ == "__main__":
    packages = [
        PackageStats("domain", 8, 1),
        PackageStats("application", 4, 4),
        PackageStats("stripe-adapter", 0, 6),
        PackageStats("shared", 11, 9),
    ]

    print(f"{'package':<16} {'Ca':>4} {'Ce':>4} {'I':>8}  lecture")
    for package in packages:
        instability = package.instability()
        reading = "stable" if instability < 0.3 else "instable" if instability > 0.7 else "intermédiaire"
        print(f"{package.name:<16} {package.ca:>4} {package.ce:>4} {instability:>8.2f}  {reading}")
