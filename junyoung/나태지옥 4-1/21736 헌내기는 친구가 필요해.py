import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

campus = []
Doyeon = (0, 0)

for i in range(N):
    campus_input = sys.stdin.readline().strip()
    if "I" in campus_input:
        Doyeon = (campus_input.index("I"), i)
    campus.append(campus_input)
    
def find_student():
    global campus, Doyeon
    
    queue = deque()
    queue.append(Doyeon)
    
    visited = [[False] * M for _ in range(N)]
    visited[Doyeon[1]][Doyeon[0]] = True
    
    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    count = 0
    
    while queue:
        x, y = queue.popleft()
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            if 0 <= nx < M and 0 <= ny < N and not visited[ny][nx]:
                if campus[ny][nx] == "P":
                    count += 1
                    queue.append((nx, ny))
                elif campus[ny][nx] == "O":
                    queue.append((nx, ny))
                visited[ny][nx] = True
                
    if count == 0:
        return "TT"
    else:
        return count
                
print(find_student())