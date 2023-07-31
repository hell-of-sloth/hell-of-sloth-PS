import sys
import heapq # 최소 힙을 사용하기 위해 heapq 모듈 사용

N, K = map(int, sys.stdin.readline().split())

jewels = []
bags = []

for _ in range(N):
    M, V = map(int, sys.stdin.readline().split())
    jewels.append((M, V))

for _ in range(K):
    C = int(sys.stdin.readline().rstrip())
    bags.append(C)

jewels.sort()
bags.sort()

temp = [] # 최소 힙을 사용하기 위해 임시 리스트 생성

result = 0

for b in bags: # 가방의 무게가 작은 것부터 비교
    while jewels and b >= jewels[0][0]:
        heapq.heappush(temp, -jewels[0][1]) # 최대 힙을 사용하기 위해 -를 붙여서 넣음
        heapq.heappop(jewels)
    if temp:
        result -= heapq.heappop(temp) # 양수 값을 얻기 위해 -를 붙여서 빼줌
        
print(result)