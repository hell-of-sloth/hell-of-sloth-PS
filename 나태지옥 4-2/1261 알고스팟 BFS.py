# 체감 난이도 7/10, 큐에 앞에 넣는다는 생각을 어떻게 한거지?, visited를 벽부순 횟수로 저장한다는 것을 생각하기까지 오래 걸림

import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

graph = []

for i in range(M):
    graph.append(list(map(int, sys.stdin.readline().strip())))

dist = [[-1] * N for _ in range(M)]  # 벽을 깬 횟수를 저장 및 방문처리

direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]


def bfs():
    queue = deque()
    queue.append((0, 0))
    dist[0][0] = 0

    while queue:
        x, y = queue.popleft()
        for dx, dy in direction:
            nx, ny = x + dx, y + dy

            if nx < 0 or nx >= M or ny < 0 or ny >= N:
                continue

            if dist[nx][ny] == -1:  # 아직 해당 방을 방문하지 않았다면
                # 만약 벽이 없다면
                if graph[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y]
                    queue.appendleft([nx, ny]) # ☆☆☆ 이 부분이 중요하다. 벽이 없는 경우에는 먼저 방문해야 하므로 appendleft를 사용

                # 만약 벽이 있다면
                else:
                    dist[nx][ny] = dist[x][y] + 1
                    queue.append([nx, ny])


bfs()
print(dist[M - 1][N - 1])