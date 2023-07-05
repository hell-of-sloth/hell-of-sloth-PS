import sys

n = int(sys.stdin.readline())

stack = []
answer = []
doable = 1
stackinput = 0
for i in range(n):
    num = int(sys.stdin.readline())
    while stackinput < num:
        stackinput += 1
        stack.append(stackinput)
        answer.append("+")

    if stack[-1] == num:
        stack.pop()
        answer.append("-")
    else:
        doable = 0
        break

if doable == 1:
    for i in answer:
        print(i)
else:
    print("NO")
