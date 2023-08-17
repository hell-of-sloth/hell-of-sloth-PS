from collections import deque

dic = {}
balloons = deque([i+1 for i in range(int(input()))])
for i, num in enumerate(input().split()):
    dic[i+1] = num
while len(balloons)>1:
    popped = balloons.popleft()
    print(popped, end=' ')
    command = dic[popped]
    if command[0] == '-':
        for i in range(int(command)*-1):
            balloons.appendleft(balloons.pop())
    else:
        for i in range(int(command)-1):
            balloons.append(balloons.popleft())
print(balloons[0])