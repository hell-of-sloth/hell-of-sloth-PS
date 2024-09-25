N, M = map(int, input().split()) # N: 정점의 개수, M: 간선의 개수

graph = [[] for _ in range(N+1)] # 인접 리스트
visited = [False] * (N+1)        # 방문 여부

for i in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

def DFS_count(): # DFS로 연결 요소의 개수 구하기
    
    stack = []
    count = 0
    
    while True:
        vertex = Is_visited()
        
        if vertex == 0:
            return count
        else:
            stack.append(vertex)
            visited[vertex] = True
            count += 1
            
            while stack:
                node = stack.pop()
                for item in graph[node]:
                    if visited[item] == False:
                        stack.append(item)
                        visited[item] = True   
    
def Is_visited(): # 방문하지 않은 정점 찾기
    global visited
    for i in range(1, N+1):
        if visited[i] == False:
            return i
    return 0

print(DFS_count())