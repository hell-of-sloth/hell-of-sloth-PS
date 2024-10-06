N = int(input())

tree = [[] for _ in range(N+1)]

for i in range(N-1):
    a, b = map(int, input().split())
    tree[a].append(b)
    tree[b].append(a)
    
def BFS():          # BFS로 트리 탐색
    global tree
    
    queue = [1]
    visited = [False] * (N+1)
    visited[1] = True
    
    parent = [0] * (N+1) # 부모 노드 저장
    
    while queue:
        node = queue.pop(0)
        for item in tree[node]:
            if visited[item] == False:
                visited[item] = True
                queue.append(item)
                parent[item] = node
    return parent

result = BFS()
for i in range(2, N+1):
    print(result[i])