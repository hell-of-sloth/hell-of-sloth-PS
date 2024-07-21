import sys
from functools import cache
input = sys.stdin.readline

@cache
def KS(n, k):
    if n == 0:
        return 0

    if k == 0:
        return 0

    if k < 0:
        return -1
    
    if n == 1:
        if k < WList[1]:
            return 0

        return VList[1]
    
    cond = KS(n - 1, k - WList[n])
    a = 0 if cond < 0 else cond + VList[n]
    b = KS(n - 1, k)
    return max(a, b) 

if __name__ == "__main__":
    N, K = map(int, input().strip().split())
    WList = [0 for _ in range(N + 1)]
    VList = [0 for _ in range(N + 1)]

    for i in range(1, N + 1):
        w, v = map(int, input().strip().split())
        WList[i] = w
        VList[i] = v
    
    print(KS(N, K))
    # 01 knapsack
    # ks = [[0 for __ in range(K + 1)] for _ in range(N + 1)]

    # for j in range(K + 1):
    #     ks[0][j] = 0

    # for i in range(N + 1):
    #     ks[i][0] = 0
    
    # for j in range(K + 1):
    #     ks[1][j] = VList[1] if j >= WList[1] else 0 

    # for i in range(2, N + 1):
    #     for j in range(1, K + 1):
    #         prev_j = j - WList[i]
    #         prev_i = i - 1
            
    #         a = 0 if prev_i <= 0 or prev_j < 0 else (ks[prev_i][prev_j] + VList[i])

    #         b = 0 if prev_i <= 0  else ks[prev_i][j]
    #         ks[i][j] = max(a, b)

    # # print(ks)
    # print(ks[N][K])