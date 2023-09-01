import sys

# sys.stdin = open("input.txt")
input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
arr = [*map(int, input().rstrip().split())]
v = int(input().rstrip())
print(f"{arr.count(v)}\n")
