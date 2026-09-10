import pytest
from discount_rules import total


@pytest.mark.parametrize(
    "initial, expected",
    [(0, 0), (99, 99), (100, 90), (250, 225)],
)
def test_applies_discount_at_the_boundary(initial, expected):
    assert total(initial) == expected


def test_rejects_negative_amount():
    with pytest.raises(ValueError, match="montant négatif"):
        total(-1)
