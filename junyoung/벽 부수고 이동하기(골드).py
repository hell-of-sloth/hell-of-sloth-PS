# 다이나믹프로그래밍을 첨가한듯
# 좌표에 거리를 기록하는 방법
# 3차원 배열을 만들어서 해당 좌표까지의 거리를 기록
# z = 0이면 벽을 뚫지 않고 간 경우, z = 1이면 벽을 뚫고 간 경우

from collections import deque

N, M = map(int, input().split())
graph = []
for i in range(N):
    graph.append(list(map(int, input())))

moves = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1]
]               # 좌표 이동을 위한 리스트

visited = [[[0] * 2 for _ in range(M)] for _ in range(N)]

def bfs():
    q = deque()
    q.append([0, 0, 0])
    visited[0][0][0] = 1

    while q:
        x, y, w = q.popleft()
        
        # 목표지점 도달 시 해당 위치까지의 거리 리턴
        if x == N - 1 and y == M - 1:
            return visited[x][y][w]

        for move in moves:
            nx, ny = x + move[0], y + move[1]

            if 0 <= nx < N and 0 <= ny < M:
                # 현재 위치로 이동할 수 있고, 아직 방문하지 않았다면
                if graph[nx][ny] == 0 and visited[nx][ny][w] == 0:
                    visited[nx][ny][w] = visited[x][y][w] + 1
                    q.append([nx, ny, w])
                
                # 현재 위치가 벽이고, 벽을 아직 부수지 않았다면
                elif graph[nx][ny] == 1 and w == 0:
                    visited[nx][ny][w + 1] = visited[x][y][w] + 1
                    q.append([nx, ny, w + 1])
    
    # 목표지점까지 도달하지 못한다면 -1 리턴
    return -1

print(bfs())