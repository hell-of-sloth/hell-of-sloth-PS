import sys
input = sys.stdin.readline

n= int(input())
a = [0]*10001
for i in range(n):
    a[int(input())]+=1

for num, count in enumerate(a):
    if count!=0:
        for i in range(count):
            print(num)