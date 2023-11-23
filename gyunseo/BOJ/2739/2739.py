import sys

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
for i in range(1, 9 + 1):
    print(f"{N} * {i} = {N*i}\n")
