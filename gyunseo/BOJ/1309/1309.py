import sys

input = sys.stdin.readline

if __name__ == "__main__":
    MOD = 9901
    N = int(input().strip())
    dp = [[0 for __ in range(3)] for _ in range(N + 1)]
    dp[1][0] = dp[1][1] = dp[1][2] = 1
    for i in range(2, N + 1):
        dp[i][0] = sum(dp[i - 1])
        dp[i][0] %= MOD
        for j in range(1, 2 + 1):
            dp[i][j] = dp[i  - 1][0] % MOD + dp[i - 1][3 - j] % MOD
            dp[i][j] %= MOD
    
    print(sum(dp[N]) % MOD)
