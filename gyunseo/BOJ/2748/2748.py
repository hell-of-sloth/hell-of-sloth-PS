import sys

input = sys.stdin.readline

if __name__ == "__main__":
    dp = [0 for _ in range(91)]
    dp[1] = 1
    dp[2] = 1
    N = int(input().rstrip())
    for i in range(3, N + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    print(dp[N])
