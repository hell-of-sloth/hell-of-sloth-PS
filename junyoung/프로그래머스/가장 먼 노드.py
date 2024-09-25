from collections import deque

def solution(n, edge):
    graph_grid = [[] for _ in range(n+1)]  # 각 노드에 대한 빈 리스트 생성
    
    for i, j in edge:
        graph_grid[i].append(j)
        graph_grid[j].append(i)
        
    visited = [False] * (n+1)
    
    q = deque([1])
    visited[1] = True
    
    result = 0
    
    while True:
        tempq = deque()
        result = len(q)
        while q:
            node = q.popleft()
            for new_node in graph_grid[node]:
                if not visited[new_node]:
                    tempq.append(new_node)
                    visited[new_node] = True
        if not tempq:
            break
        q = tempq
    
    return result
