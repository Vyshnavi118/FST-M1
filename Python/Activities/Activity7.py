numbers = input("Enter a sequence of comma separated values: ").split(",")

total = 0
for number in numbers:
    total += int(number)

print(total)
