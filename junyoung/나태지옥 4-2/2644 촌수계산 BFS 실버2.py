# 체감 난이도 2/10, BFS 이용, 인접리스트 이용
# 56ms

import sys
from collections import deque

def solve():
    n = int(sys.stdin.readline().strip())
    p1, p2 = map(int, sys.stdin.readline().split())
    m = int(sys.stdin.readline().strip())
    
    graph = [[] for _ in range(n + 1)]
    visited = [False] * (n + 1)
    
    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)
        
    q = deque([(p1, 0)])
    visited[p1] = True
    
    while q:
        x, cnt = q.popleft()
        
        if x == p2:
            print(cnt)
            return
        
        for nx in graph[x]:
            if not visited[nx]:
                visited[nx] = True
                q.append((nx, cnt + 1))
                
    print(-1)
    
solve()
    