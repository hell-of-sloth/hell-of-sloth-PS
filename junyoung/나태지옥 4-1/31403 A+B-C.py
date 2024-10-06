from sys import stdin

A = stdin.readline().strip()
B = stdin.readline().strip()
C = int(stdin.readline().strip())

print(int(A) + int(B) - C)
print(int(A + B) - C)