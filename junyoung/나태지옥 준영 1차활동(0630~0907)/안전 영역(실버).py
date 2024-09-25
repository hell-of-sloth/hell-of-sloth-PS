import sys # 시간 줄이기
from collections import deque

N = int(sys.stdin.readline().rstrip())

area = []
max_safe_area = []
max_height = 0 # 최대 높이
min_height = 101 # 최소 높이

for i in range(N):
    area.append(list(map(int, sys.stdin.readline().split())))
    Mh = max(area[i])
    mh = min(area[i])
    if max_height < Mh:
        max_height = Mh
    if min_height > mh:
        min_height = mh
    
def Find_Safe_Area(): # 안전 영역 찾기
    global max_height, min_height, max_safe_area
    
    max_count = 0
    
    for rheight in range(min_height-1, max_height):
        count = 0
        visited_area = [[False] * N for _ in range(N)] # 방문 표시
        while True:
            start_x, start_y = Find_Start(rheight, visited_area)
            if start_x == -1 and start_y == -1:
                break
            visited_area = BFS(start_x, start_y, visited_area, rheight)
            count += 1
        if max_count < count:
            max_count = count
        
    return max_count
        
def BFS(start_x, start_y, visited, rain_height): # BFS
    global area
    
    queue = deque()
    direction = [(0, 1), (1, 0), (0, -1), (-1, 0)] # 방향
    visited[start_y][start_x] = True
    queue.append((start_x, start_y))
    
    while queue:
        x, y = queue.popleft()
        for dx, dy in direction:
            nx = x + dx
            ny = y + dy
            
            if 0 <= nx < N and 0 <= ny < N:
                if area[ny][nx] > rain_height and visited[ny][nx] == False: # 물에 잠기지 않은 곳
                    visited[ny][nx] = True
                    queue.append((nx, ny))
                    
    return visited
        
def Find_Start(rain_height, visited): # 시작점 찾기
    global area
    
    for i in range(N):
        for j in range(N):
            if area[i][j] > rain_height and visited[i][j] == False: # 물에 잠기지 않은 곳
                return j, i
    
    return -1, -1
        
print(Find_Safe_Area())