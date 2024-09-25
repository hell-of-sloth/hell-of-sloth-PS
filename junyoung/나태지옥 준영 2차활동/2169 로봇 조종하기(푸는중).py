import sys

N, M = map(int, sys.stdin.readline().split())

mars = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

dp = [[0, 0] * M for _ in range(N)]
# dp[i][j][0] : (i, j)까지 왔을 때, 왼쪽에서 오는 경우
# dp[i][j][1] : (i, j)까지 왔을 때, 오른쪽에서 오는 경우
dp[0][0][0] = mars[0][0]    
dp[0][0][1] = mars[0][0]

def DP_cal():
    # 첫 째줄 처리
    for j in range(M):
        if j == 0:
            continue
        dp[0][j][0] = dp[0][j-1][0] + mars[0][j]
    
    # 중간 줄 처리 (여기서부터 다시)
    for i in range(1, N-1):
        dp[i][0][0] = dp[i-1][0][0] + mars[i][0]
        
        for j in range(1, M+1):
            if j == 0:
                dp[i][j][0] = dp[i-1][j][0] + mars[i][j]
            else:
                dp[i][j][0] = max(dp[i-1][j][0], dp[i][j-1][0]) + mars[i][j]
        for j in range(M-1, -1, -1):
            if j == M-1:
                dp[i][j][1] = dp[i-1][j][1] + mars[i][j]
            else:
                dp[i][j][1] = max(dp[i-1][j][1], dp[i][j+1][1]) + mars[i][j]
    
    # 마지막 줄 처리      
    for j in range(M):
        if j == 0:
            temp = max(dp[N-1][j][0], dp[N-1][j][1]) + mars[N][j]
            dp[i][j][0] = temp
        else:
            dp[i][j][0] = max(dp[N-1][j][0], dp[N-1][j][1], dp[N][j-1][0]) + mars[i][j]

            # 10 35 37 45 58