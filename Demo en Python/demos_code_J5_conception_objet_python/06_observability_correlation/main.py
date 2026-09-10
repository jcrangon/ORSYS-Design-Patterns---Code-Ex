
from collections import Counter
from datetime import datetime, timezone


class Metrics:
    def __init__(self) -> None:
        self._counters: Counter[str] = Counter()

    def inc(self, key: str) -> None:
        self._counters[key] += 1

    def dump(self) -> None:
        for key, value in self._counters.items():
            print(f"METRIC {key}={value}")


def log(level: str, trace_id: str, event: str, details: str) -> None:
    timestamp = datetime.now(timezone.utc).isoformat()
    print(f"{timestamp} level={level} traceId={trace_id} event={event} {details}")


def main() -> None:
    metrics = Metrics()
    trace_id = "trace-7f3a"

    log("INFO", trace_id, "reservation.requested", "room=A101 user=Alice")
    metrics.inc("reservation.requested")

    log("INFO", trace_id, "payment.accepted", "amount=120")
    log("INFO", trace_id, "reservation.confirmed", "reservationId=R-42")
    metrics.inc("reservation.confirmed")

    metrics.dump()
    print("À commenter : sans traceId, reconstituer le parcours distribué devient coûteux.")


if __name__ == "__main__":
    main()
