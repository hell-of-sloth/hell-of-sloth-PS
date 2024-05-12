import sys

input = sys.stdin.readline
MOD = 10_007
if __name__ == "__main__":
    N = int(input().rstrip())
    dp = [[0 for _ in range(10)] for __ in range(N + 1)]
    for i in range(10):
        dp[1][i] = 1
    for i in range(2, N + 1):
        for j in range(10):
            tmp_sum = 0
            for k in range(j, 10):
                tmp_sum += dp[i - 1][k]
            dp[i][j] = tmp_sum % MOD
    ans = 0
    for i in range(10):
        # print(f"dp[{N}][{i}]: {dp[N][i]}")
        ans += dp[N][i] % MOD
    print(ans % MOD)
