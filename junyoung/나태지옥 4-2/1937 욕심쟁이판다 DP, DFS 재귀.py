# 체감 난이도 5/10, Dp, DFS를 사용해서 풀려고 시도 -> 재귀 DFS 성공, 재귀 DFS랑 반복문 DFS랑 방식이 다른 것도 이해해야 할 듯;;
# 힙, dp로도 풀리지만 이게 더 빠름, 760ms 정도

import sys
sys.setrecursionlimit(1000000) # 재귀 제한 늘려줌

n = int(sys.stdin.readline().strip())

graph = []

dp = [[0] * n for _ in range(n)]

for i in range(n):
    graph.append(list(map(int, sys.stdin.readline().split())))
    
direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]

# 재귀 DFS
def dfs(x, y):
    global direction, graph
    
    # 이미 방문한 곳이면 그 값 리턴
    if dp[x][y]:
        return dp[x][y]
    
    # 방문한 적 없으면 1로 초기화
    dp[x][y] = 1
    for dx, dy in direction:
        nx, ny = dx + x, dy + y
        if 0 <= nx < n and 0 <= ny < n and graph[nx][ny] > graph[x][y]:
            dp[x][y] = max(dp[x][y], dfs(nx, ny)+1) # 현재 값과 다음의 DFS를 비교해서 큰 값으로 갱신
            
    return dp[x][y]


for i in range(n):
    for j in range(n):
        if dp[i][j] == 0: # 방문한 적 없으면 DFS 실행
            dfs(i, j)

print(max([max(i) for i in dp]))