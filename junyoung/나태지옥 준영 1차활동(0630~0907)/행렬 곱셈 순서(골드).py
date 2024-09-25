import sys

N = int(sys.stdin.readline().rstrip())

matrix = []
dp = [[0] * N for _ in range(N)] # 2차원 배열 동적계획법

for _ in range(N):
    r, c = map(int, sys.stdin.readline().split())
    matrix.append((r, c))

    
for i in range(1, N): # 개 야랄난 알고리주ㅡㅁ
    for j in range(N-i):
        if i == 1:
            dp[j][j+i] = matrix[j][0] * matrix[j][1] * matrix[j+i][1]
            continue
        
        dp[j][j+i] = float('inf') # 무제한 부여
        for k in range(j, j+i):
            dp[j][j+i] = min(dp[j][j+i], dp[j][k] + dp[k+1][j+i] + matrix[j][0] * matrix[k][1] * matrix[j+i][1])
        
print(dp[0][N-1]) # 끝값이 답