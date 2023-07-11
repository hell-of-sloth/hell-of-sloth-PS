import sys
import heapq

n = int(sys.stdin.readline())
stacks = []

for _ in range(n):
    heapq.heappush(stacks, int(sys.stdin.readline()))
answer = 0
while len(stacks)>1:
    temp = heapq.heappop(stacks)+heapq.heappop(stacks)
    answer+=temp
    heapq.heappush(stacks, temp)

print(answer)