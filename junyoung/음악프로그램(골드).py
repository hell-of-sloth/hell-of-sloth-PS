import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N+1)]

for _ in range(M):
    tmp = list(map(int, sys.stdin.readline().split()))
    for i in range(2, len(tmp)):
        if tmp[i-1] not in graph[tmp[i]]: # 중복되는 노드가 있을 수 있으므로
            graph[tmp[i]].append(tmp[i-1])

def Find_Alone(): # 노드 중에 자신을 가리키는 노드가 없는 노드 찾기
    global graph
    
    alone_list = []
    
    for i in range(1, N+1):
        if len(graph[i]) == 0:
            alone_list.append(i)
            
    return alone_list

def Topological_Sort(): # 위상정렬 이용
    global graph
    
    result = []
    
    alone_list = Find_Alone()
    
    if alone_list:
        queue = deque(alone_list)
    else:
        return False
    
    while queue:
        node = queue.popleft()
        
        result.append(node)
        
        for i in range(1, N+1):
            if node in graph[i]:
                graph[i].remove(node)
                if len(graph[i]) == 0:
                    queue.append(i)
                    
    if len(result) == N: # 모든 노드를 방문했는지 확인
        return result
    else:
        return False
       
result = Topological_Sort() 
if result: # 위상정렬 결과 출력
    for i in result:
        print(i) 
else: # 위상정렬 결과가 없다면 0 출력
    print(0)