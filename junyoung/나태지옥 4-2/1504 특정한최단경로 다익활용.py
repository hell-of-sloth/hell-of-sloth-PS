# 체감 난이도 7/10, 다익인건 알았는데 활용하기 어려웠다.., 그리고 생각보다 시간초과 안나네?
# E = 2000000 x 3번 -> 352ms?

import sys
import heapq

N, E = map(int, sys.stdin.readline().split())

graph = [[0 for _ in range(N+1)] for _ in range(N+1)]

for i in range(E):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a][b] = c
    graph[b][a] = c
    
u, v = map(int, sys.stdin.readline().split())

def djik(start):
    global N, E, graph
    
    # 거리저장 배열
    dist = [0] + [float('inf') for _ in range(N)]
    dist[start] = 0
    
    # 먼저 초기값 넣어주기
    hq = [(0, start)]
    
    while hq:
        cost, i = heapq.heappop(hq)
        
        # 처음에는 0 == 0 이어서 통과됨, 이외에 값 다르면 pass
        if cost != dist[i]:
            continue
        
        for idx in range(1, N+1):
            if graph[i][idx]:
                # 가중치 + 전 dist vs 원래 dist 값 비교
                if dist[idx] > graph[i][idx] + dist[i]:
                    dist[idx] = graph[i][idx] + dist[i]
                    heapq.heappush(hq, (graph[i][idx] + dist[i], idx))

    return dist # 거리 리스트 반환

one_dist = djik(1)  # 1 에서 시작하는 최단거리
u_dist = djik(u)    # u 에서 시작하는 최단거리
v_dist = djik(v)    # v 에서 시작하는 최단거리

# 1 -> u -> v -> N / 1 -> v -> u -> N
first_case = one_dist[u] + u_dist[v] + v_dist[N]
second_case = one_dist[v] + v_dist[u] + u_dist[N]

result = min(first_case, second_case)

# 길이 없을 경우 inf가 나옴
if result == float('inf'):
    print(-1)
else:
    print(result)