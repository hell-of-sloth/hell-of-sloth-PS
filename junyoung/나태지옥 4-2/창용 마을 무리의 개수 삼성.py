# 체감 난이도 2/10, 유니온 파인드를 알고있으면 쉬움, 몇 개의 유니온이 있는지 세는 문제


T = int(input())

def solve():
    N, M = map(int, input().split())
    
    parent = [i for i in range(N)]
    
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
    
    for c in range(M):
        x, y = map(int, input().split())
        x -= 1
        y -= 1
        union(x, y)
        
    cnt = 0
    
    for i in range(N):
        if parent[i] == i:
            cnt += 1
            
    return cnt

for t in range(1, T+1):
    print("#{} {}".format(t, solve()))
