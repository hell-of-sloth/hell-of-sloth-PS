# 체감 난이도 4/10, MST를 알고있어서 쉬웠음 
# 다만 처음에 Prim으려 하려다가 Prim으로 처리하기 힘든 케이스가 있어 Kruskal로 바꿈
# 4
# 0X00
# 00Y0
# 0000
# 00Z0
# 이 케이스의 경우 Prim으로 처리가 어려움, 방향그래프라 그런것도 있는 듯

# deque 없이 처리하기 도전
# deque 없애고 for문으로 sort한 리스트 처리 -> 성공
# 60ms -> 36ms 약 40% 정도 빨라짐

import sys

N = int(sys.stdin.readline().strip())

val_L = []
total = 0

for i in range(N):
    L = list(sys.stdin.readline().strip())
    for j in range(N):
        val = 0
        if L[j] == '0':
            continue
        elif 65 <= ord(L[j]) <= 90:
            val = ord(L[j]) - 38
        else:
            val = ord(L[j]) - 96
        val_L.append((val, i, j))
        total += val
    
val_L.sort()
    
# for i in val_L:
#     print(i, end=' ')

parent = [i for i in range(N)]

# Union Find
def find(x):
    if parent[x] == x:
        return x
    parent[x] = find(parent[x])
    
    return parent[x]

def union(x, y):
    x = find(x)
    y = find(y)
    
    if x < y:
        parent[y] = x
    else:
        parent[x] = y
        
def is_same_parent(x, y):
    x = find(x)
    y = find(y)
    
    if x == y:
        return True
    return False
        
# Kruskal Algorithm, deque 없애고 for문으로 sort한 리스트 처리
def kruskal():
    global N, val_L, total
    
    total_edges = N - 1
    
    for w, x, y in val_L:
        if total_edges == 0:
            break
        
        if is_same_parent(x, y):
            continue
        
        union(x, y)
        total -= w
        total_edges -= 1
    
    if total_edges == 0:
        print(total)
    else:
        print(-1)
    
kruskal()
    

# Prim Algorithm
# def prim():
#     global N, matrix
    
#     visited = [0] * N
    
#     q = []
#     result = 0
    
#     for i in range(N):
#         if matrix[0][i] != 0:
#             heapq.heappush(q, (matrix[0][i], i))
            
#     visited[0] = 1
    
#     while q:
#         w, v = heapq.heappop(q)
        
#         if visited[v] == 1:
#             continue
        
#         visited[v] = 1
#         result += w
        
#         for i in range(N):
#             if matrix[v][i] != 0:
#                 heapq.heappush(q, (matrix[v][i], i))
                
#     print(result)
#     print(visited)
    
# prim()