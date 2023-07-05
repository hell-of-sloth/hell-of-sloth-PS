import sys # 시간 줄이기용

n = int(sys.stdin.readline().rstrip())

tree = [[] for _ in range(n+1)] # 트리
M = 0                           # 최대 거리

for i in range(n-1):
    a, b, c = map(int, sys.stdin.readline().split())
    tree[a].append((b, c)) # a에서 b로 가는 거리 c
    tree[b].append((a, c)) # b에서 a로 가는 거리 c
      
def DFS(start): # DFS로 트리의 지름 구하기
    global tree, n
    
    visited = [False] * (n+1) # 방문 여부
    visited[start] = True
    distance = [0] * (n+1) # 거리
    
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
        
print(DFS(DFS(1)[0])[1]) # 트리의 지름 출력, 어차피 1에서 제일 먼것에서 찾는 것이 가장 먼것임(?)