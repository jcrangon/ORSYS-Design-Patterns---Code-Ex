from abc import ABC, abstractmethod


class ReportService(ABC):
    @abstractmethod
    def get(self, report_id: str) -> str:
        pass


class RealReportService(ReportService):
    def get(self, report_id: str) -> str:
        print("[REAL] chargement coûteux")
        return f"Rapport-{report_id}"


class CacheProxy(ReportService):
    def __init__(self, target: ReportService) -> None:
        self._target = target
        self._cache: dict[str, str] = {}

    def get(self, report_id: str) -> str:
        if report_id not in self._cache:
            self._cache[report_id] = self._target.get(report_id)
        return self._cache[report_id]


class LoggingDecorator(ReportService):
    def __init__(self, target: ReportService) -> None:
        self._target = target

    def get(self, report_id: str) -> str:
        print(f"[LOG] demande {report_id}")
        return self._target.get(report_id)


class ReportFacade:
    def __init__(self, reports: ReportService) -> None:
        self._reports = reports

    def display_twice(self, report_id: str) -> None:
        print(f"1) {self._reports.get(report_id)}")
        print(f"2) {self._reports.get(report_id)}")


def main() -> None:
    service: ReportService = LoggingDecorator(CacheProxy(RealReportService()))
    ReportFacade(service).display_twice("A42")
    print("\nÀ commenter : Decorator=journaliser, Proxy=cacher, Facade=simplifier l'orchestration.")


if __name__ == "__main__":
    main()
