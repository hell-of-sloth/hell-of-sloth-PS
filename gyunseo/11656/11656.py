import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

S = input().rstrip()
N = len(S)

for s in sorted(S[i:N] for i in range(N)):
    print(f"{s}\n")
