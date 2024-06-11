import sys

input = sys.stdin.readline

if __name__ == "__main__":
    T = int(input().rstrip())
    for _ in range(T):
        n = int(input().rstrip())
        board = [[*map(int, input().rstrip().split())] for _ in range(2)]
        dp = [[0 for __ in range(n)] for _ in range(2)]
        # 기저 상황 설정

        dp[0][0] = board[0][0]
        dp[1][0] = board[1][0]
        if n == 1:
            pass
        else:
            dp[0][1] = dp[1][0] + board[0][1]
            dp[1][1] = dp[0][0] + board[1][1]

            for i in range(2, n):
                dp[0][i] = (
                    max(dp[1][i - 1], max(dp[1][i - 2], dp[0][i - 2])) + board[0][i]
                )
                dp[1][i] = (
                    max(dp[0][i - 1], max(dp[1][i - 2], dp[0][i - 2])) + board[1][i]
                )

        # for i in range(2):
        #     for j in range(n):
        #         print(dp[i][j], end=" ")
        #     print()
        print(max(dp[0][n - 1], dp[1][n - 1]))
