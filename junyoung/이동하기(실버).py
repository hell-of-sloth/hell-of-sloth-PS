import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

maze = []
dp = [[0] * M for _ in range(N)] # dp

for i in range(N):
    maze.append(list(map(int, sys.stdin.readline().split())))

queue = deque()
queue.append((0, 0))
dp[0][0] = maze[0][0]
check = [[False] * M for _ in range(N)]

while queue:
    x, y = queue.popleft()
    if check[x][y]:
        continue
    check[x][y] = True

    if x == N - 1 and y == M - 1:
        print(dp[x][y])
        break

    for dx, dy in [(1, 0), (0, 1), (1, 1)]:
        nx, ny = dx + x, dy + y
        if 0 <= nx < N and 0 <= ny < M:
            if dp[x][y] + maze[nx][ny] > dp[nx][ny]:
                dp[nx][ny] = dp[x][y] + maze[nx][ny]
            queue.append((nx, ny))