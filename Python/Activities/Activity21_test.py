import pytest

def test_addition():
    num1 = 8
    num2 = 12
    sum = num1 + num2
    assert sum == 20

def test_subtraction():
    num1 = 90
    num2 = 45
    diff = num1 - num2
    assert diff == 45

def test_multiplication():
    num1 = 7
    num2 = 6
    prod = num1 * num2
    assert prod == 42

def test_division():
    num1 = 144
    num2 = 12
    quot = num1 / num2
    assert quot == 12
