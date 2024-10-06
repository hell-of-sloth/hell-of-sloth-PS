import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

board = [0] * 101
for i in range(N):
    x, y = map(int, sys.stdin.readline().split())
    board[x] = y
    
for i in range(M):
    u, v = map(int, sys.stdin.readline().split())
    board[u] = v
    
def BFS():
    global board

    q = deque()
    q.append(1)
    temp_q = deque()
    visited = [False] * 101
    visited[1] = True
    
    count = 0
    
    while q:
        x = q.popleft()
        
        for i in range(1, 7):
            nx = x + i
            
            if visited[nx]:
                continue
            
            if board[nx] != 0:
                nx = board[nx]
                
            if nx == 100:
                return count + 1
            elif nx < 100:
                temp_q.append(nx)
                visited[nx] = True
            else:
                continue
            
        if not q:
            q = temp_q
            temp_q = deque()
            count += 1
            
print(BFS())