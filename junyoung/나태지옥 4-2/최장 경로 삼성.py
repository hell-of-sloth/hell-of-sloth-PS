# 체감 난이도 6/10, DFS를 이용한 최장 경로 찾기 문제, 하지만 백트랙킹을 적용한듯 하다.

T = int(input())

def dfs(v, cnt):
    global ans
    
    if cnt > ans:
        ans = cnt
        
    for nv in graph[v]:
        if not visited[nv]:
            visited[nv] = 1
            dfs(nv, cnt+1)
            visited[nv] = 0

for t in range(1, T+1):
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    
    for _ in range(m):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)
        
    ans = 0
    visited = [0] * (n + 1)
    
    for i in range(1, n+1):
        visited[i] = 1
        dfs(i, 1)
        visited[i] = 0
        
    print('#{} {}'.format(t, ans))

