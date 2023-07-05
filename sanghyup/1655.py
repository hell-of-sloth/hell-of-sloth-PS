import sys
import heapq

n = int(sys.stdin.readline())

left = []
right = []
for i in range(n):
    num = int(sys.stdin.readline())

    if len(left) == len(right):
        heapq.heappush(left, -num)
    else:
        heapq.heappush(right, num)

    if right and right[0] < -left[0]:
        temp = -heapq.heappop(right)
        heapq.heappush(right, -heapq.heappop(left))
        heapq.heappush(left, temp)
    print(-left[0])