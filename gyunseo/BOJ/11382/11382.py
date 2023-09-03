import sys

input = sys.stdin.readline
print = sys.stdout.write

A, B, C = map(int, input().rstrip().split())
print(f"{A+B+C}\n")
