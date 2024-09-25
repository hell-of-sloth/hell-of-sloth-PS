import sys

N, M, R = map(int, sys.stdin.readline().split()) # 정점의 개수, 간선의 개수, 시작 정점 번호

edges = [[] for _ in range(N + 1)] # 간선 정보
vertex = [0] * (N + 1) # 정점 방문 순서 저장

for _ in range(M):
    u, v = map(int, sys.stdin.readline().split())
    edges[u].append(v)
    edges[v].append(u)
    
for i in range(1, N + 1):
    if edges[i]:
        edges[i].sort(reverse=True) # 오름차순으로 방문하기 위해 역순으로 정렬
    
def DFS(): # DFS 이용 방문순서를 vertex 리스트에 저장
    global N, M, R, edges, vertex
    
    count = 1
    
    stack = []
    visited = [False] * (N + 1) # 방문처리
    stack.append(R) # 시작 정점 추가
    
    while stack:
        vert = stack.pop()
        if visited[vert]: # 이미 방문한 정점이면 건너뜀
            continue
        vertex[vert] = count
        count += 1 # 방문 순서 증가
        visited[vert] = True
        
        for i in edges[vert]:
            stack.append(i)
            
DFS() # DFS실행

for i in range(1, N + 1):
    print(vertex[i])