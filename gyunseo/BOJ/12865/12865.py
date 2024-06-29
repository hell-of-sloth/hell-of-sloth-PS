import sys

input = sys.stdin.readline


class Solution:
    def knapsack(self):
        dp = [[0 for __ in range(K + 1)] for _ in range(N + 1)]

        for i in range(1, N + 1):
            for j in range(1, K + 1):
                if j < items[i][0]:
                    dp[i][j] = dp[i - 1][j]
                else:
                    dp[i][j] = max(
                        dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]
                    )
        print(dp[N][K])


if __name__ == "__main__":
    N, K = map(int, input().strip().split())
    items = [(0, 0)]
    for _ in range(N):
        w, v = map(int, input().strip().split())
        items.append((w, v))
    items.sort()
    sol = Solution()
    sol.knapsack()
