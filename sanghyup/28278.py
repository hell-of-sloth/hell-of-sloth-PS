import sys

input = sys.stdin.readline

stack = []

for i in range(int(input())):
    inputed = input().strip().split()
    if inputed[0] == '1':
        stack.append(inputed[1])
    elif inputed[0] == '2':
        if len(stack):
            print(stack.pop(-1))
        else:
            print(-1)
    elif inputed[0] == '3':
        print(len(stack))
    elif inputed[0] == '4':
        if len(stack) == 0:
            print(1)
        else:
            print(0)
    elif len(stack):
        print(stack[-1])
    else:
        print(-1)        