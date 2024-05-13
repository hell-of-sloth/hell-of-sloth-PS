import sys
from heapq import heappush, heappop

N = int(sys.stdin.readline().strip())

hq = []

for _ in range(N):
    x = int(sys.stdin.readline().strip())
    if x == 0:
        if hq:
            element = heappop(hq)
            if element[1] == 1:
                print(element[0])
            else:
                print(-element[0])
        else:
            print(0)
    else:
        if x > 0:
            heappush(hq, (x, 1))
        else:
            heappush(hq, (-x, -1))