import sys
from collections import deque

cols, rows =  map(int, sys.stdin.readline().split())
space = cols*rows

box = []

q = deque([])
last = 0
row = 0
for _ in range(rows):
    for i, tomato in enumerate(sys.stdin.readline().rstrip().split()):
        tomato = int(tomato)
        box.append(tomato)
        if tomato == 1:
            q.append(row+i)
    row+=cols

while q:
    t = q.popleft()
    if t+cols<space:
        if box[t+cols] == 0:
            box[t+cols] = box[t]+1
            q.append(t+cols)
    if (t%cols)<(cols-1):
        if box[t+1] == 0:
            box[t+1] = box[t]+1
            q.append(t+1)
    if (t%cols)>(0):
        if box[t-1] == 0:
            box[t-1] = box[t]+1
            q.append(t-1)
    if (t-cols)>= 0 :
        if box[t-cols]==0:
            box[t-cols] = box[t]+1
            q.append(t-cols)
answer = 0
for i in box:
    if i == 0:
        print(-1)
        exit(0)
    answer = max(answer, i)
print(answer - 1)