from pathlib import Path

TYPE_MAP = {
    "String": "str",  # accepte aussi le vocabulaire de la démo Java
    "str": "str",
    "int": "int",
    "boolean": "bool",
    "bool": "bool",
    "double": "float",
    "float": "float",
}


def generate(model: Path, output_dir: Path) -> Path:
    lines = model.read_text(encoding="utf-8").splitlines()
    if not lines or not lines[0].startswith("entity "):
        raise ValueError("modèle invalide")

    name = lines[0][len("entity "):].strip()
    fields = []
    for line in lines[1:]:
        parts = line.strip().split()
        if len(parts) == 3 and parts[0] == "field":
            field_name, field_type = parts[1], parts[2]
            fields.append((field_name, TYPE_MAP.get(field_type, field_type)))

    body = ["from dataclasses import dataclass", "", "", "@dataclass(frozen=True)", f"class {name}:"]
    if fields:
        body.extend(f"    {field_name}: {field_type}" for field_name, field_type in fields)
    else:
        body.append("    pass")
    code = "\n".join(body) + "\n"

    output_dir.mkdir(parents=True, exist_ok=True)
    generated = output_dir / f"{name.lower()}.py"
    generated.write_text(code, encoding="utf-8")
    return generated
