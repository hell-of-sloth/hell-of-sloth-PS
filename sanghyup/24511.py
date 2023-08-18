from collections import deque
_ = (input())

queue = deque([])

for i in zip(input().split(), input().split()):
    if i[0] == '0':
        queue.append(i[1])
_ = input()
userin = input().split()
for i in userin:
    queue.appendleft(i)


for i in range(len(userin)):
    print(queue.pop(), end = ' ')