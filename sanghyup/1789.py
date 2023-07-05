import sys
S = int(sys.stdin.readline())
num = 1
next = 2
while num <= S:
    num+=next
    next+=1
print(next-2)