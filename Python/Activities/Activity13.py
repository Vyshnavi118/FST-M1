def calculate_sum(numbers):
    total = 0
    for number in numbers:
        total += number
    return total

numList = [10, 40, 60, 90]

result = calculate_sum(numList)

print("The sum of all the elements is: " + str(result))
