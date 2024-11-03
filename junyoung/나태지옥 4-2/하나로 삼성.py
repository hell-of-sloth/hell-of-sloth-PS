# 체감 난이도 4/10, 거리를 계산 +  MST를 이용해서 풀어야 하는 문제
# 노드 개수 체크하면서 모든 노드 방문하면 종료하게 하면 빨리 끝낼 수 있음

from heapq import heappush, heappop

T = int(input())

def solve():
    N = int(input())
    x_list = list(map(int, input().split()))
    y_list = list(map(int, input().split()))
    E = float(input())
    
    visited = [False] * N
    
    hq = [(0, 0)]  # (cost, node)
    total_cost = 0
    node_count = 0 # 방문한 노드의 수
    
    while hq:
        if node_count == N: # 모든 노드를 방문했으면 종료
            break
        
        cost, node = heappop(hq)
        if visited[node]:
            continue
        
        visited[node] = True
        node_count += 1
        total_cost += cost
        
        for i in range(N):
            if not visited[i]:
                new_cost = (x_list[node] - x_list[i]) ** 2 + (y_list[node] - y_list[i]) ** 2
                heappush(hq, (new_cost, i))
                
    result = total_cost * E
    return round(result)

for t in range(1, T + 1):
    print("#{} {}".format(t, solve()))
