# 정점 1000, 간선 100000 - 그래프, 근데 한 점에서 다른점까지의 최소 거리? -> 다익스트라
# 체감 난이도 1/10, 걍 다익스트라 알면 쉬움


import sys
from heapq import heappush, heappop

N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())

graph = [[] for _ in range(N+1)]

for _ in range(M):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))
    
s, e = map(int, sys.stdin.readline().split())
    
def djik(start):
    dist = [float('inf')] * (N+1)
    dist[start] = 0
    hp = [(0, start)]
    
    while hp:
        cost, vertex = heappop(hp)
        
        if dist[vertex] != cost:
            continue
        
        for v, w in graph[vertex]:
            if dist[v] > cost + w:
                dist[v] = cost + w
                heappush(hp, (dist[v], v))
                
    return dist

print(djik(s)[e])
