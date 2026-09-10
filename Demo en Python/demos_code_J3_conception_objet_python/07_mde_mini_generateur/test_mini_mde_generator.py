import py_compile
import pytest
from mini_mde_generator import generate


def test_generates_a_python_dataclass_from_the_model(tmp_path):
    model = tmp_path / "model.txt"
    model.write_text("entity Order\nfield id str\nfield total int\n", encoding="utf-8")

    generated = generate(model, tmp_path / "generated")
    code = generated.read_text(encoding="utf-8")

    assert "class Order" in code
    assert "id: str" in code
    assert "total: int" in code


def test_generated_artifact_really_compiles(tmp_path):
    model = tmp_path / "model.txt"
    model.write_text("entity Order\nfield id str\nfield total int\n", encoding="utf-8")
    generated = generate(model, tmp_path / "generated")

    py_compile.compile(str(generated), doraise=True)


def test_rejects_invalid_model(tmp_path):
    model = tmp_path / "bad.txt"
    model.write_text("not an entity", encoding="utf-8")

    with pytest.raises(ValueError, match="modèle invalide"):
        generate(model, tmp_path / "generated")
