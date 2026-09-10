import pytest
from shipping import LegacyShippingCalculator, RefactoredShippingCalculator

CASES = [(0, 0), (1, 7), (4, 7), (5, 9), (10, 19)]


@pytest.mark.parametrize("weight, expected", CASES)
def test_characterizes_current_legacy_behavior(weight, expected):
    assert LegacyShippingCalculator().price(weight) == expected


@pytest.mark.parametrize("weight, expected", CASES)
def test_refactored_version_preserves_characterized_behavior(weight, expected):
    assert RefactoredShippingCalculator().price(weight) == expected
