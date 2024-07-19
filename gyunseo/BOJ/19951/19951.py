import sys

input = sys.stdin.readline
from bisect import bisect_left, bisect_right
if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    HList = [0] + [*map(int, input().strip().split())]
    prefixSumList = [0 for _ in range(N + 1 + 1)]

    for _ in range(M):
        a, b, k = map(int, input().strip().split())
        prefixSumList[a] += k
        prefixSumList[b + 1] += -k
    
    for i in range(1, N + 1):
        prefixSumList[i + 1] += prefixSumList[i]
    
    for i in range(1, N + 1):
        print(HList[i] + prefixSumList[i], end = " ")