import sys
from collections import deque

input = list(map(int,sys.stdin.readline().split()))

timeTaken = [0 for i in range(100001)]

bfs = deque()
bfs.append(input[0])
while True:
    now = bfs.popleft()
    if now == input[1]:
        print(timeTaken[now])
        break
    for next in [now-1,now+1,now*2]:
        if 0<=next and next<=100000 and timeTaken[next] == 0:
            timeTaken[next] = timeTaken[now] + 1
            bfs.append(next)