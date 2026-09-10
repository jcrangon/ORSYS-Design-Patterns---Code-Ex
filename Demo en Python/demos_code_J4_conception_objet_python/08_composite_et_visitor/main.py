from __future__ import annotations
from abc import ABC, abstractmethod


class Node(ABC):
    @abstractmethod
    def accept(self, visitor: "Visitor") -> None:
        pass


class FileNode(Node):
    def __init__(self, name: str, size: int) -> None:
        self.name = name
        self.size = size

    def accept(self, visitor: "Visitor") -> None:
        visitor.visit_file(self)


class Folder(Node):
    def __init__(self, name: str) -> None:
        self.name = name
        self.children: list[Node] = []

    def add(self, node: Node) -> "Folder":
        self.children.append(node)
        return self

    def accept(self, visitor: "Visitor") -> None:
        visitor.visit_folder(self)
        for child in self.children:
            child.accept(visitor)


class Visitor(ABC):
    @abstractmethod
    def visit_file(self, file: FileNode) -> None:
        pass

    @abstractmethod
    def visit_folder(self, folder: Folder) -> None:
        pass


class SizeVisitor(Visitor):
    def __init__(self) -> None:
        self.total = 0

    def visit_file(self, file: FileNode) -> None:
        self.total += file.size
        print(f"file {file.name} size={file.size}")

    def visit_folder(self, folder: Folder) -> None:
        print(f"folder {folder.name}")


def main() -> None:
    root = (
        Folder("root")
        .add(FileNode("a.txt", 10))
        .add(Folder("images").add(FileNode("x.png", 90)))
    )

    visitor = SizeVisitor()
    root.accept(visitor)
    print(f"Total={visitor.total}")
    print("À commenter : ajouter une opération Visitor n'oblige pas à modifier FileNode/Folder.")


if __name__ == "__main__":
    main()
