import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())

paper = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
checked = set()
for i in range(n):
        for j in range(m):
            if paper[i][j] == 1:
                checked.add((i, j))
                
def find_start():
    if checked:
        return checked.pop()
    return False     
    

def BFS():
    global checked
    
    sqaure = 0
    
    q = deque()
    start = find_start()
    if not start:
        return False
    q.append(start)
    
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    while q:
        x, y = q.popleft()
        
        sqaure += 1
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < m and paper[nx][ny] == 1 and (nx, ny) in checked:
                q.append((nx, ny))
                checked.remove((nx, ny))
                
    return sqaure

count = 0
max_square = 0

while True:
    sqaure = BFS()
    if not sqaure:
        break
    count += 1
    max_square = max(max_square, sqaure)
    
print(count)
print(max_square)