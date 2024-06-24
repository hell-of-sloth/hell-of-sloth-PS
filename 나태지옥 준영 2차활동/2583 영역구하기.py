import sys
from collections import deque

M, N, K = map(int, sys.stdin.readline().split())

area = [[0] * N for _ in range(M)]
zero_point = set([(i, j) for i in range(N) for j in range(M)])

for _ in range(K):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    
    for y in range(y1, y2):
        for x in range(x1, x2):
            area[y][x] = 1
            zero_point.discard((x, y))

def find_area():
    global zero_point
    
    if len(zero_point) == 0:
        return False
    else:
        return zero_point.pop()
    
def bfs():
    global area, zero_point
    
    queue = deque()
    visited = [[False] * N for _ in range(M)]
               
    start_point = find_area()
    if not start_point:
        return False
    
    queue.append(start_point)
    visited[start_point[1]][start_point[0]] = True
    
    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    count = 1
    
    while queue:
        x, y = queue.popleft()
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < M and not visited[ny][nx] and area[ny][nx] == 0:
                visited[ny][nx] = True
                count += 1
                zero_point.remove((nx, ny))
                queue.append((nx, ny))
    
    return count
    
answer = []

while True:
    result = bfs()
    if not result:
        break
    answer.append(result)
    
print(len(answer))
print(" ".join(map(str, sorted(answer))))