import sys
from functools import cache

sys.setrecursionlimit(10**6)
input = sys.stdin.readline


@cache
def dfs(n, k):
    if n == 1:
        return 1
    tmp_sum = 0
    for i in range(k, 10):
        tmp_sum += dfs(n - 1, i) % MOD
        tmp_sum %= MOD
    return tmp_sum % MOD


if __name__ == "__main__":
    MOD = 10_007
    N = int(input().strip())
    ans = 0
    for i in range(10):
        ans += dfs(N, i)
        ans %= MOD
    print(ans % MOD)
