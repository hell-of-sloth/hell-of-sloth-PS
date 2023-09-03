import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N, X = map(int, input().rstrip().split())

A = [*map(int, input().rstrip().split())]

for num in filter(lambda x: x < X, A):
    print(f"{num} ")
print(f"\n")
