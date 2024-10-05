# 체감 난이도 3/10, 그리디 인줄 알았더니 브루트포스였던것

import sys
from itertools import permutations

N = int(sys.stdin.readline().strip())
L = list(map(int, sys.stdin.readline().split()))

per = permutations(L)
result = 0

# 순열마다 차이를 더해(s), ans 보다 크면 ans를 update
for i in per:
    temp = 0
    for j in range(len(i)-1):
        temp += abs(i[j]-i[j+1])
    if temp > result:
        result = temp

# check = [0 for _ in range(N)]
# q = []
# result = 0

# for i in range(N):
#     for j in range(i, N):
#         if i == j:
#             continue
#         heapq.heappush(q, (-abs(L[i] - L[j]), i, j)) 
        
# while q:
#     cost, i, j = heapq.heappop(q)
    
#     if not 0 in check:
#         break
    
#     if check[i] == 2 or check[j] == 2:
#         continue
    
#     result += (-cost)
#     check[i] += 1
#     check[j] += 1
    
print(result)
    

        
