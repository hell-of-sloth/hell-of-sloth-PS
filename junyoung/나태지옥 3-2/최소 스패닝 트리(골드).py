import sys # 이거 안쓰니까 시간초과 남;;

V, E = map(int, sys.stdin.readline().split())

parent = [i for i in range(V+1)]
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



for i in range(E):
    a, b, c = map(int, sys.stdin.readline().split())
    vertex_value.append((a, b, c))
    
vertex_value.sort(key=lambda x: x[2])

# 크루스칼 알고리즘(최소 신장 트리)
def Minimum_Spanning_Tree():
    global vertex_value
    
    result = 0
    for a, b, c in vertex_value:
        if not Same_Parent(a, b):
            Union_Parent(a, b)
            result += c
    return result

print(Minimum_Spanning_Tree())