from collections import deque
sung = int(input())

line = deque(map(int, input().split()))

stack = []

order = 1
while len(line):
    i = line.popleft()
    if i == order:
        order+=1
        continue
    elif len(stack):
        if stack[-1] == order:
            stack.pop()
            line.appendleft(i)
            order+=1
            continue
        elif i < stack[-1]:
            stack.append(i)
            continue
        else:
            print('Sad')
            exit()
    else:
        stack.append(i)
while stack:
    if stack.pop() != order:
        print('Sad')
        exit()
    else: order+=1
print('Nice')