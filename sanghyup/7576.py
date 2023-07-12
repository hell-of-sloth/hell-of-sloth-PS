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
            last = row+i
    row+=cols

count =0
while len(ripe)<(space):
    change = False
    t = q.popleft()
    if t == last:
        count+=1
    if t+cols<space:
        if box[t+cols] == 0:
            change = True
            box[t+cols] = 1
            q.append(t+cols)
            ripe.add(t+cols)
    if (t%cols)<(cols-1):
        if box[t+1] == 0:
            change = True
            box[t+1] = 1
            q.append(t+1)
            ripe.add(t+1)
    if (t%cols)>(0):
        if box[t-1] == 0:
            change = True
            box[t-1] = 1
            q.append(t-1)
            ripe.add(t-1)
    if (t-cols)> 0 :
        if box[t-cols]==0:
            change = True
            box[t-cols]=1
            q.append(t-cols)
            ripe.add(t-cols)
    if(change):
        for row in range(rows):
            for col in range(cols):
                print(box[row*cols +col], end='')
            print()
        print(t,ripe)
        print()