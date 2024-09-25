import sys

N = int(sys.stdin.readline().rstrip())

ropes = []

for i in range(N):
    ropes.append(int(sys.stdin.readline().rstrip()))
    
ropes.sort(reverse=True)

max_weight = 0

for i in range(N):
    if ropes[i] * (i+1) > max_weight:
        max_weight = ropes[i] * (i+1)

print(max_weight)