import sys

input = sys.stdin.readline

_ = input()
cranes = list(map(int, input().split()))
_ = input()
boxes = list(map(int, input().split()))

cranes.sort(reverse=True)
boxes.sort(reverse=True)
count = 0
while boxes:
    for crane in cranes:
        for i, box in enumerate(boxes):
            if crane>=box:
                del boxes[i]
                break
    count+=1
print(count)