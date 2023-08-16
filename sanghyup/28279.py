from collections import deque
import sys

input = sys.stdin.readline
deq = deque()
for i in range(int(input())):
    command = input().strip().split()
    if command[0] == '1':
        deq.appendleft(command[1])
    elif command[0] == '2':
        deq.append(command[1])
    elif command[0] == '5':
        print(len(deq))
    elif len(deq): 
        if command[0] == '6':
            print(0)
        elif command[0] == '3':
            print(deq.popleft())
        elif command[0] == '4':
            print(deq.pop())
        elif command[0] == '7':
            print(deq[0])
        elif command[0] == '8':
            print(deq[-1])
    elif command[0] == '6':
        print(1)
    else:
        print(-1)
    