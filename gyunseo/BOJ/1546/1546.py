import sys

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
scores = list(map(int, input().rstrip().split()))
max_score = max(scores)


def sejunize(n):
    return n / max_score * 100


scores = list(map(sejunize, scores))
print(f"{sum(scores)/N}\n")
