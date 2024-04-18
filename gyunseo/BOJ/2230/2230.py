import sys
from bisect import bisect_left

input = sys.stdin.readline
MAX = 2 * int(1e9) + 1
minAns = MAX


def OOB(j):
    if j < 0 or j >= N:
        return True
    return False


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    A = sorted([int(input().rstrip()) for _ in range(N)])
    e = 0
    for s in range(len(A)):
        while not OOB(e) and A[e] - A[s] < M:
            e += 1
        if e == len(A):
            break
        minAns = min(minAns, A[e] - A[s])

    print(minAns)
