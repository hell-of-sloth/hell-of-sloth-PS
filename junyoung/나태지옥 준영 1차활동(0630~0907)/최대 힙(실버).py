import heapq
import sys

N = int(input())

heap = []
for i in range(N):
    x = int(sys.stdin.readline().rstrip())
    if x:
        heapq.heappush(heap, (-x, x))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)