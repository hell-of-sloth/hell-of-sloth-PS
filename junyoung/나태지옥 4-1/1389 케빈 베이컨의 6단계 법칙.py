import sys

N, M = map(int, sys.stdin.readline().split())

friends = [[0 for _ in range(N+1)] for _ in range(N+1)]

for i in range(M):
    A, B = map(int, sys.stdin.readline().split())
    friends[A][B] = 1
    friends[B][A] = 1
    

# 플로이드 워셜 이용
for k in range(1, N+1):
    for i in range(1, N+1):
        for j in range(1, N+1):
            if friends[i][k] and friends[k][j]:
                if friends[i][j] == 0:
                    friends[i][j] = friends[i][k] + friends[k][j]
                else:
                    friends[i][j] = min(friends[i][j], friends[i][k] + friends[k][j])
                
result = 1000000000
for i in range(1, N+1):
    temp = sum(friends[i])
    if result > temp:
        result = temp
        idx = i
        
print(idx)