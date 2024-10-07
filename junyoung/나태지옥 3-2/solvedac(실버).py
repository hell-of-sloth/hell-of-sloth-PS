import sys
import heapq # 최소 힙으로 구현

n = int(sys.stdin.readline().rstrip())

opinion = []
result = 0

julsa = round(n * 0.15 + 0.0000001) # 절삭할 수 반올림(파이썬에서는 0.5초과만 반올림하기 때문에 쥰내작은 수를 더해서 이상일때도 반올림하게 함)
pyung = n - 2 * julsa # 평균을 구할 때 나눌 수

for _ in range(n):
    heapq.heappush(opinion, int(sys.stdin.readline().rstrip()))
  
# 절삭할 수만큼 빼줌  
for _ in range(julsa):
    heapq.heappop(opinion)

# 평균을 구할 때 나눌 수만큼 더해줌
for _ in range(pyung):
    result += heapq.heappop(opinion)

# pyung이 0일 경우 0으로 나누는 것을 방지
if pyung == 0:
    result = 0
else:
    result = round(result / pyung + 0.0000001)

print(result) # 응애