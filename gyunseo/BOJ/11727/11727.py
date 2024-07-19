import sys

input = sys.stdin.readline
MOD = 10_007
if __name__ == "__main__":
    N = int(input().rstrip())
    dp = [0 for _ in range(1001)]
    dp[1] = 1
    dp[2] = 3
    for i in range(3, N + 1):
        dp[i] = (dp[i - 1] % MOD + 2 * dp[i - 2] % MOD) % MOD
    print(dp[N])