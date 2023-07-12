import sys
from collections import deque

cols, rows =  map(int, sys.stdin.readline().split())
space = cols*rows

box = []
ripe = set()

q = deque([])
last = 0
row = 0
for _ in range(rows):
    for i, tomato in enumerate(sys.stdin.readline().rstrip().split()):
        tomato = int(tomato)
        box.append(tomato) # 필요할까?
        if tomato == 1:
            ripe.add(row+i)
            q.append(row+i)
    row+=cols

while q:
    change = False
    t = q.popleft()
    if t+cols<space:
        if box[t+cols] == 0:
            change = True
            box[t+cols] = box[t]+1
            q.append(t+cols)
            ripe.add(t+cols)
    if (t%cols)<(cols-1):
        if box[t+1] == 0:
            change = True
            box[t+1] = box[t]+1
            q.append(t+1)
            ripe.add(t+1)
    if (t%cols)>(0):
        if box[t-1] == 0:
            change = True
            box[t-1] = box[t]+1
            q.append(t-1)
            ripe.add(t-1)
    if (t-cols)> 0 :
        if box[t-cols]==0:
            change = True
            box[t-cols] = box[t]+1
            q.append(t-cols)
            ripe.add(t-cols)
    # if(change):
    #     for row in range(rows):
    #         for col in range(cols):
    #             print(box[row*cols +col], end='')
    #         print()
        # print(t,ripe)
        # print(q)
        # print()
answer = 0
for i in box:
    if i == 0:
        print(-1)
        exit(0)
    answer = max(answer, i)
print(answer - 1)