
import json
from typing import Protocol


class ExportPlugin(Protocol):
    @property
    def format(self) -> str:
        ...

    def export(self, data: str) -> str:
        ...


class CsvPlugin:
    format = "csv"

    def export(self, data: str) -> str:
        return "CSV:" + data.replace(" ", ",")


class JsonPlugin:
    format = "json"

    def export(self, data: str) -> str:
        return json.dumps({"value": data}, ensure_ascii=False, separators=(",", ":"))


class ExportCore:
    def __init__(self) -> None:
        self._plugins: dict[str, ExportPlugin] = {}

    def register(self, plugin: ExportPlugin) -> None:
        self._plugins[plugin.format] = plugin

    def export(self, format_: str, data: str) -> str:
        plugin = self._plugins.get(format_)
        if plugin is None:
            raise ValueError(f"format inconnu: {format_}")
        return plugin.export(data)


def main() -> None:
    core = ExportCore()
    core.register(CsvPlugin())
    core.register(JsonPlugin())

    print(core.export("csv", "Alice A101"))
    print(core.export("json", "Alice A101"))
    print("À commenter : ajouter XML ne nécessite pas de modifier ExportCore.")


if __name__ == "__main__":
    main()
