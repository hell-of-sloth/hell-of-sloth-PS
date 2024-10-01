import sys

n = int(sys.stdin.readline().strip())

graph = []
dp = [[0] * n for _ in range(n)]

for i in range(n):
    graph.append(list(map(int, sys.stdin.readline().split())))

direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]

def DFS(x, y):
    if dp[x][y] != 0:
        return dp[x][y]
    
    stack = [(x, y)]
    while stack:
        cx, cy = stack.pop()
        if dp[cx][cy] == 0:
            dp[cx][cy] = 1  # 현재 위치에서 시작하는 경우 최소 거리는 1
        
        for dx, dy in direction:
            nx, ny = cx + dx, cy + dy
            if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] > graph[cx][cy]:
                if dp[nx][ny] == 0:
                    stack.append((nx, ny))
                dp[cx][cy] = max(dp[cx][cy], dp[nx][ny] + 1)
    
    return dp[x][y]

# 모든 위치에서 DFS를 시작
for i in range(n):
    for j in range(n):
        if dp[i][j] == 0:
            DFS(i, j)

print(max(max(row) for row in dp))