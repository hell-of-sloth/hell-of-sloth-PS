import sys
from collections import deque

n = int(sys.stdin.readline().rstrip())

node = [] # 노드의 좌표를 저장할 리스트
parent = [i for i in range(n)]
value_list = [] # 간선의 길이를 저장할 리스트

for _ in range(n):
    x, y = map(float, sys.stdin.readline().split())
    node.append((x, y))
    
for i in range(n-1):
    for j in range(i+1, n):
        value = ((node[i][0] - node[j][0])**2 + (node[i][1] - node[j][1])**2)**0.5
        value_list.append((i, j, value)) # 노드의 번호와 간선의 길이를 저장
        
value_list.sort(key=lambda x: x[2]) # 간선의 길이를 기준으로 정렬

# 유니온 파인드 기법 (부모를 이용한 사이클 찾기)
 
def Get_Parent(x): # 부모를 찾는 함수
    global parent
    
    if parent[x] == x:
        return x
    parent[x] = Get_Parent(parent[x])
    return parent[x]

def Union_Parent(a, b): # 부모를 합치는 함수
    global parent
    
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
    

    
    
# 크루스칼 알고리즘(최소 신장 트리)
def Minimum_Spanning_Tree():
    global value_list
    
    result = 0
    for a, b, c in value_list:
        if not Same_Parent(a, b):
            Union_Parent(a, b)
            result += c
            
    return result

print(Minimum_Spanning_Tree())