from sys import stdin

a  = stdin.readline().strip()
b = stdin.readline().strip()
c = stdin.readline().strip()

result = 0

if a.isdigit():
    result= int(a) + 3
elif b.isdigit():
    result= int(b) + 2
elif c.isdigit():
    result= int(c) + 1
    
if result % 3 == 0 and result % 5 == 0:
    print("FizzBuzz")
elif result % 3 == 0:
    print("Fizz")
elif result % 5 == 0:
    print("Buzz")
else:
    print(result)