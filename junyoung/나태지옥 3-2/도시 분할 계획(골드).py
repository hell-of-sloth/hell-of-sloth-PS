# 최소 스패닝 트리를 이용

import sys

N, M = map(int, sys.stdin.readline().split())

parent = [i for i in range(N+1)]
vertex_value = []

# 유니온 파인드 기법 (부모를 이용한 사이클 찾기)
 
def Get_Parent(x):
    if parent[x] == x:
        return x
    parent[x] = Get_Parent(parent[x])
    return parent[x]

def Union_Parent(a, b):
    a = Get_Parent(a)
    b = Get_Parent(b)

    if a < b: # 작은 쪽이 부모가 된다.
        parent[b] = a
    else:
        parent[a] = b 
        
def Same_Parent(a, b):
    if Get_Parent(a) == Get_Parent(b):
        return True
    else:
        return False

for i in range(M):
    a, b, c = map(int, sys.stdin.readline().split())
    vertex_value.append((a, b, c))
    
vertex_value.sort(key=lambda x: x[2])

# 크루스칼 알고리즘(최소 신장 트리)
def Minimum_Spanning_Tree():
    global vertex_value
    
    result = []
    for a, b, c in vertex_value:
        if not Same_Parent(a, b):
            Union_Parent(a, b)
            result.append(c)
    result.pop() # 도시를 두 개로 쪼개니까 가장 큰 길 하나를 제거
    return sum(result)

print(Minimum_Spanning_Tree())