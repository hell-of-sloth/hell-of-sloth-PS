import sys
import math

input = sys.stdin.readline

MAX = int(1e9) + 7
if __name__ == "__main__":
    N = int(input().rstrip())
    dp = [MAX for _ in range(int(1e5) + 1)]
    dp[0] = 0
    for i in range(1, N + 1):
        for j in range(1, int(math.sqrt(N)) + 1):
            if dp[i - j * j] + 1 < dp[i]:
                dp[i] = dp[i - j * j] + 1
    print(dp[N])
