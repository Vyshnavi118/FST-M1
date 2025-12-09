import pytest

@pytest.fixture
def num_list():
    int_list = [2, 4, 6, 8, 10]
    return int_list

def test_sum(num_list):
    sum = 0
    for n in num_list:
        sum += n
    assert sum == 30
