# 체감 난이도 5/10, BFS 이용
# 3000ms in python3
# 빙산 갱신 과정에서 0이 된것을 중복으로 계산 할 수 있어서 두 번 갱신 했더니 시간이 좀 걸리는 것 같음 -> 이것을 최적화 하는 방법이 없을까?
# 어쨌든 성공 -> 오래걸려서 BFS가 아닌가 했는데? BFS가 맞음


import sys
from collections import deque

def solve():
    N, M = map(int, sys.stdin.readline().split())
    
    graph = []
    year = 0
    glaciers = deque()
    
    for i in range(N):
        L = list(map(int, sys.stdin.readline().split()))
        graph.append(L)
        for j in range(M):
            if L[j] != 0:
                glaciers.append((i, j, L[j]))
                
    
    def melt(x, y): # 주변 0 계산
        count = 0
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] == 0:
                count += 1
                
        return count
    
    def BFS(x, y): # 이어진 빙산 개수 계산
        
        q = deque([(x, y)])
        visited = set()
        visited.add((x, y))
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        count = 1
        
        while q:
            x, y = q.popleft()
            for dx, dy in directions:
                nx, ny = x + dx, y + dy
                if 0 <= nx < N and 0 <= ny < M and graph[nx][ny] != 0 and (nx, ny) not in visited:
                    visited.add((nx, ny))
                    q.append((nx, ny))
                    count += 1  
                    
        return count
    
    for i in range(len(glaciers)): # 주변 0 개수 갱신
        x, y, value = glaciers.popleft()
        glaciers.append((x, y, value, melt(x, y)))     
    
    while True:
        if len(glaciers) == 0:
            print(0)
            break
        
        if BFS(glaciers[0][0], glaciers[0][1]) != len(glaciers):
            print(year)
            break
        
        for i in range(len(glaciers)):
            x, y, value, melt_value = glaciers.popleft()
            if value - melt_value > 0:
                graph[x][y] = value - melt_value
                glaciers.append((x, y, value - melt_value, melt(x, y)))
            else:
                graph[x][y] = 0
                
        for i in range(len(glaciers)):
            x, y, value, melt_value = glaciers.popleft()
            glaciers.append((x, y, value, melt(x, y)))
                 
        year += 1
        
solve()