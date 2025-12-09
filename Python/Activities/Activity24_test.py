import pytest

@pytest.fixture
def wallet_amount():
    amount = 0
    return amount

@pytest.mark.parametrize("earned, spent, expected", [
    (50, 15, 35),
    (100, 40, 60),
    (75, 25, 50)
])
def test_transactions(wallet_amount, earned, spent, expected):
    wallet_amount += earned
    wallet_amount -= spent
    assert wallet_amount == expected
