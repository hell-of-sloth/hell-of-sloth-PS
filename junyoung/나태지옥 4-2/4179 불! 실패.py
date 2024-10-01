import sys
from collections import deque

R, C = map(int, sys.stdin.readline().split())

is_fire = [[False] * C for _ in range(R)]

graph = []
j_queue = deque()
f_queue = deque()

for i in range(R):
    L = list(sys.stdin.readline().strip())
    for j in range(C):
        if L[j] == 'F':
            is_fire[i][j] = True
            f_queue.append((i, j))
        elif L[j] == 'J':
            j_queue.append((i, j, 0))
    graph.append(L)

def BFS():
    
    global j_queue, f_queue
    
    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    
    
    visited = [[False] * C for _ in range(R)]
    visited[j_queue[0][0]][j_queue[0][1]] = True
    
    while True:
        new_j_queue = deque()
        new_f_queue = deque()
        
        while j_queue:
            x, y, t = j_queue.popleft()
            
            if is_fire[x][y]:
                continue
            
            if x == 0 or x == R - 1 or y == 0 or y == C - 1:
                print(t + 1)
                return
            
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                if 0 <= nx < R and 0 <= ny < C and not is_fire[nx][ny] and graph[nx][ny] == '.':
                    if not visited[nx][ny]:
                        new_j_queue.append((nx, ny, t + 1))
                        visited[nx][ny] = True
                
        while f_queue:
            if not new_j_queue:
                print('IMPOSSIBLE')
                return
            
            x, y = f_queue.popleft()
            
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                if 0 <= nx < R and 0 <= ny < C and not graph[nx][ny] == '#' and not is_fire[nx][ny]:
                    is_fire[nx][ny] = True
                    new_f_queue.append((nx, ny))
                    
        j_queue = new_j_queue
        f_queue = new_f_queue
            
BFS()