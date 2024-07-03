from sys import stdin
from collections import deque

def knight_move(I, start_x, start_y, end_x, end_y):
    
    count = 0
    
    visited = [[False for _ in range(I)] for _ in range(I)]
    q = deque()
    q.append((start_x, start_y))
    visited[start_x][start_y] = True
    
    if start_x == end_x and start_y == end_y: # 시작점과 도착점이 같은 경우
        return 0
    
    while True: # 사이클 수를 세기위한 while문
        new_q = deque() # 다음 사이클을 위한 큐
        while q: # BFS
            x, y = q.popleft()
            
            for dx, dy in [(2, 1), (2, -1), (-2, 1), (-2, -1), (1, 2), (1, -2), (-1, 2), (-1, -2)]:
                nx, ny = x + dx, y + dy
                
                if nx < 0 or nx >= I or ny < 0 or ny >= I:
                    continue
                
                if nx == end_x and ny == end_y:
                    return count+1
                
                if not visited[nx][ny]:
                    visited[nx][ny] = True
                    new_q.append((nx, ny)) # 다음 사이클을 위한 큐에 추가
                    
        if not new_q: # 다음 사이클에 큐가 비어있으면 == 이동할 수 없는 경우
            break
        q = new_q
        count += 1

N = int(stdin.readline().strip())

for _ in range(N):
    I = int(stdin.readline().strip())
    sx, sy = map(int, stdin.readline().split())
    ex, ey = map(int, stdin.readline().split())
    print(knight_move(I, sx, sy, ex, ey))
