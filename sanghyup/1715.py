import sys
from collections import deque

n = int(sys.stdin.readline())
stacks = []

for _ in range(n):
    stacks.append(int(sys.stdin.readline()))

stacks = sorted(stacks)
answer = 0
while len(stacks)>1:
    temp = stacks[0]+stacks[1]
    answer+=temp
    del stacks[0]
    del stacks[0]
    stacks.append(temp)
    sorted(stacks)

print(answer)