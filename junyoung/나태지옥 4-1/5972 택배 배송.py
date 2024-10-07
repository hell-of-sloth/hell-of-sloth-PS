'''
농부 현서는 농부 찬홍이에게 택배를 배달해줘야 합니다. 
그리고 지금, 갈 준비를 하고 있습니다. 평화롭게 가려면 가는 길에 
만나는 모든 소들에게 맛있는 여물을 줘야 합니다. 
물론 현서는 구두쇠라서 최소한의 소들을 만나면서 지나가고 싶습니다.

농부 현서에게는 지도가 있습니다. N (1 <= N <= 50,000) 개의 헛간과, 
소들의 길인 M (1 <= M <= 50,000) 개의 양방향 길이 그려져 있고, 
각각의 길은 C_i (0 <= C_i <= 1,000) 마리의 소가 있습니다. 
소들의 길은 두 개의 떨어진 헛간인 
A_i 와 B_i (1 <= A_i <= N; 1 <= B_i <= N; A_i != B_i)를 잇습니다. 
두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있습니다. 
농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있습니다.

농부 현서의 지도가 주어지고, 지나가는 길에 소를 만나면 
줘야할 여물의 비용이 주어질 때 최소 여물은 얼마일까요? 
농부 현서는 가는 길의 길이는 고려하지 않습니다.
'''

import sys
from heapq import heappush, heappop

N, M = map(int, sys.stdin.readline().split())

array_graph = [[] for _ in range(N)]

for i in range(M):
    A, B, C = map(int, sys.stdin.readline().split())
    array_graph[A-1].append((B-1, C))
    array_graph[B-1].append((A-1, C))
    
def dijkstra(start):
    global array_graph
    
    distance = [float('inf')] * N
    distance[start] = 0
    queue = []
    heappush(queue, (0, start))
    
    while queue:
        current_distance, current_node = heappop(queue)
        
        # 현재 거리보다 더 긴 거리라면 무시
        if distance[current_node] < current_distance:
            continue
        
        for next_node, next_distance in array_graph[current_node]:
            # 현재 노드를 거쳐서 다음 노드로 이동하는 거리가 더 짧은 경우
            if current_distance + next_distance < distance[next_node]:
                distance[next_node] = current_distance + next_distance
                heappush(queue, (distance[next_node], next_node))

    return distance

dijkstra_result = dijkstra(0)
print(dijkstra_result[N-1])