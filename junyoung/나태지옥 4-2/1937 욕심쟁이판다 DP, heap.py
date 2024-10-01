# 체감 난이도 5/10, DP, heapq를 사용해서 풀었음, 힙 부시, 팝 때문에 시간이 좀 걸림 1300ms 정도

import sys
import heapq

n = int(sys.stdin.readline().strip())

hq = []
graph = []

dp = [[0] * n for _ in range(n)]

# 힙 정렬을 위해 힙에 넣어줌
for i in range(n):
    l = list(map(int, sys.stdin.readline().split()))
    graph.append(l)
    for j in range(n):
        heapq.heappush(hq, (l[j], i, j))
        
direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]

# 작은 값부터 주위 갱신
while hq:
    val, i, j = heapq.heappop(hq)
    if dp[i][j] == 0:
        dp[i][j] = 1
    for dx, dy in direction:
        x, y = i + dx, j + dy
        if 0 <= x < n and 0 <= y < n and graph[x][y] > graph[i][j]:
            dp[x][y] = max(dp[x][y], dp[i][j] + 1)

print(max([max(i) for i in dp]))