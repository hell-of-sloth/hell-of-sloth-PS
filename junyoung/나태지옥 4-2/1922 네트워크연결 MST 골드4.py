# 체감 난이도 3/10, MST 문제, 프림 알고리즘 활용
# 264ms
# 방문 체크 및 hq에 넣는 조건 숙달

import sys
from heapq import heappop, heappush

def solve():
    N = int(sys.stdin.readline().strip())
    M = int(sys.stdin.readline().strip())
    
    # 인접 리스트 활용이 더 빠름
    connections = [[] for _ in range(N + 1)]
    for _ in range(M):
        a, b, c = map(int, sys.stdin.readline().split())
        connections[a].append((b, c))
        connections[b].append((a, c))
        
    def Prim():
        answer = 0
        visited = [False] * (N + 1)
        hq = []
        heappush(hq, (0, 1)) # (cost, node) 초기화
        nodes = 0
        
        while hq:
            if nodes == N: # 모든 노드를 방문했으면 종료
                break
            
            cost, node = heappop(hq)
            
            if visited[node]: # 방문 했으면 넘어가기
                continue
            
            visited[node] = True
            answer += cost
            nodes += 1
            
            for next_node, next_cost in connections[node]:
                if not visited[next_node]:
                    heappush(hq, (next_cost, next_node))

        return answer
    
    print(Prim())

solve()