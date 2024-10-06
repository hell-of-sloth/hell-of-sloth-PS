import sys

V = int(sys.stdin.readline().rstrip())

tree = [[] for _ in range(V+1)] # 트리
M = 0                           # 최대 거리

for i in range(V):
    temp = list(map(int, sys.stdin.readline().split()))
    for item in range(1, len(temp)-1, 2):
        tree[temp[0]].append((temp[item], temp[item+1]))
        
def DFS(start): # DFS로 트리의 지름 구하기
    global tree, V
    
    visited = [False] * (V+1) # 방문 여부
    visited[start] = True
    distance = [0] * (V+1) # 거리
    
    stack = [start]
    
    while stack:
        node = stack.pop()
        for next_node, next_dist in tree[node]:
            if not visited[next_node]:
                visited[next_node] = True
                distance[next_node] = distance[node] + next_dist
                stack.append(next_node)
    
    M = max(distance) # 최대 거리
    return distance.index(M), M # 최대 거리의 인덱스와 최대 거리

print(DFS(DFS(1)[0])[1])