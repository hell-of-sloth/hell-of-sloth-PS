import sys

input = sys.stdin.readline


if __name__ == "__main__":
    MOD = 10_007
    N = int(input().strip())
    dp = [[0 for __ in range(10)] for _ in range(N + 1)]
    for i in range(10):
        dp[1][i] = 1

    if N <= 1:
        print(sum(dp[N]))
        exit(0)
    for i in range(10):
        dp[2][i] = 10 - i

    for i in range(3, N + 1):
        for j in range(10):
            for k in range(j, 10):
                # 길이가 i이고, 첫 숫자가 j인 오름수의 개수는
                # 길이가 지금 거보다 하나작고, 첫 숫자가 j보다 크거나 같은 오름수들의 합이다.
                dp[i][j] += dp[i - 1][k] % MOD
                dp[i][j] %= MOD
    ret = 0
    for n in dp[N]:
        ret += n % MOD
    print(ret % MOD)
