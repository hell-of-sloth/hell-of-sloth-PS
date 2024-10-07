import sys

N = int(sys.stdin.readline().rstrip())

tower = list(map(int, sys.stdin.readline().split()))
tower_signal = [0] * N
stack = []

for i in range(N-1, -1, -1):
    temp = (tower[i], i+1)
    while stack:
        if stack[-1][0] < temp[0]:
            tower_signal[stack[-1][1]-1] = temp[1]
            stack.pop()
        else:
            break
    stack.append(temp)
    
for rest in stack:
    tower_signal[rest[1]-1] = 0

print(*tower_signal)