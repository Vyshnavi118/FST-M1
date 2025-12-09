import pytest

def test_addition():
    num1 = 14
    num2 = 9
    sum = num1 + num2
    assert sum == 23

def test_subtraction():
    num1 = 80
    num2 = 30
    diff = num1 - num2
    assert diff == 50

@pytest.mark.activity
def test_multiplication():
    num1 = 9
    num2 = 7
    prod = num1 * num2
    assert prod == 63

@pytest.mark.activity
def test_division():
    num1 = 225
    num2 = 15
    quot = num1 / num2
    assert quot == 15
