import sys
from collections import deque
        
graph = []
visited = []    
    
def Find_Land(): # 시작 땅 찾기
    global graph, visited
    for i in range(h):
        for j in range(w):
            if graph[i][j] == 1 and visited[i][j] == False: # 땅이고 방문하지 않았다면
                return j, i # x, y 리턴
    return -1, -1 # 땅이 없다면 -1, -1 리턴

def BFS(): # BFS
    global graph, visited
    
    count = 0 # 섬 개수
    queue = deque()
    
    # 상하좌우, 대각선
    direction = [(1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (-1, -1), (1, -1), (-1, 1)]
    while True: # 땅이 없을 때까지
        start_x, start_y = Find_Land()
        if start_x == -1 and start_y == -1: # 땅이 없다면 break
            break
        queue.append((start_x, start_y))
        visited[start_y][start_x] = True
        while queue:
            x, y = queue.popleft()
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                if 0 <= nx < w and 0 <= ny < h:
                    if graph[ny][nx] == 1 and visited[ny][nx] == False:
                        queue.append((nx, ny)) # 큐에 추가
                        visited[ny][nx] = True # 방문 처리
        count += 1 # 섬 개수 증가
    return count # 섬 개수 리턴
    
while True:
    w, h = map(int, sys.stdin.readline().split())
    if w == 0 and h == 0: # 0 0 입력 시 종료
        break
    graph = []
    visited = [[False] * w for _ in range(h)]
    for i in range(h):
        graph.append(list(map(int, sys.stdin.readline().split())))
    print(BFS())