import sys
from collections import deque

M, N ,H = map(int, sys.stdin.readline().split())

box = []
for _ in range(H):
    box.append([list(map(int, sys.stdin.readline().split())) for _ in range(N)])
    
def find_tomatoes():
    global box
    
    tomatoes = []
    
    for i in range(H):
        for j in range(N):
            for k in range(M):
                if box[i][j][k] == 1:
                    tomatoes.append((i, j, k))
                    
    return tomatoes


def bfs():
    global box
    
    directions = [(0, 0, 1), (0, 0, -1), (0, 1, 0), (0, -1, 0), (1, 0, 0), (-1, 0, 0)]
    visited = [[[False] * M for _ in range(N)] for _ in range(H)]
    
    tomatoes = find_tomatoes()
    for tomato in tomatoes:
        visited[tomato[0]][tomato[1]][tomato[2]] = True
    
    queue = deque()
    queue.extend(tomatoes)
    temp_queue = deque()
    
    cnt_days = 0
    
    while queue:
        z, y, x = queue.popleft()
        
        for dz, dy, dx in directions:
            nz, ny, nx = z + dz, y + dy, x + dx
            
            if 0 <= nz < H and 0 <= ny < N and 0 <= nx < M and box[nz][ny][nx] == 0 and not visited[nz][ny][nx]:
                box[nz][ny][nx] = box[z][y][x] + 1
                temp_queue.append((nz, ny, nx))
                visited[nz][ny][nx] = True
        
        if not queue and temp_queue:
            queue.extend(temp_queue)
            temp_queue.clear()
            cnt_days += 1
        
    for i in range(H):
        for j in range(N):
            for k in range(M):
                if box[i][j][k] == 0:
                    return -1
                
    return cnt_days
    
print(bfs())