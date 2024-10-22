# N = 300,000 / M = 1000000, MlogM + M
# 체감 난이도 2/10, 다익스트라 쉬운 문제

import sys
from heapq import heappop, heappush

N, M, K, X = map(int, sys.stdin.readline().split())
graph = {}

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    if graph.get(a) == None:
        graph[a] = []
    graph[a].append(b)
    
def djik(start):
    hq = [(0, start)]
    dist = [float('inf')] * (N+1)
    dist[start] = 0
    
    while hq:
        d, now_v = heappop(hq)
        
        if dist[now_v] != d:
            continue
        
        if graph.get(now_v) == None:
            continue
        else:
            for next_v in graph[now_v]:
                if dist[next_v] > d + 1:
                    dist[next_v] = d + 1
                    heappush(hq, (d+1, next_v))
                    
    return dist

result = djik(X)
answer = []
for i in range(1, N+1):
    if result[i] == K:
        answer.append(i)

if len(answer) == 0:
    print(-1)
else:
    for i in answer:
        sys.stdout.write(str(i) + '\n')