import sys

N = int(sys.stdin.readline().strip())

walls = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

def DP():
    dp = [[[0, 0, 0] for _ in range(N)] for _ in range(N)]

    # 0: 가로, 1: 세로, 2: 대각선
    dp[0][1][0] = 1
    
    for i in range(N):
        for j in range(2, N):
            if walls[i][j] == 0:
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2]
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2]
                if walls[i-1][j] == 0 and walls[i][j-1] == 0:
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2]
                    
    return dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]

print(DP())