from collections import deque

def solution(maps):
    N, M = len(maps), len(maps[0])
    
    q = deque()
    q.append((0, 0, 1))
    visited = [[False] * M for _ in range(N)]
    visited[0][0] = 1
    
    while q:
        x, y, cnt = q.popleft()
        
        for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < M and maps[nx][ny] == 1 and visited[nx][ny] == False:
                visited[nx][ny] = True
                if nx == N - 1 and ny == M - 1:
                    return cnt + 1
                q.append((nx, ny, cnt + 1))
                
    return -1
        