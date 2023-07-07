import sys
from collections import deque

n = int(sys.stdin.readline())

for _ in range(n):
    funcArray = sys.stdin.readline().rstrip()
    until = int(sys.stdin.readline())
    arr = deque((sys.stdin.readline().rstrip()[1:-1].split(','))[0:until])
    print(arr)
    if n == 0:
        queue = []
    front = True
    flag = True
    for func in funcArray:
        if func == 'R':
            front = not front
        elif func == 'D':
            if len(arr) == 0:
                print("error")
                flag = False
                break
            elif front:
                arr.popleft()
            else:
                arr.pop()
    if not front:
        arr.reverse()
    if flag:
        print('['+','.join(arr) + ']')