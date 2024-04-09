import sys

N = int(sys.stdin.readline().strip())

heights = []
value = [0] * N

for _ in range(N):
    heights.append(int(sys.stdin.readline().strip()))
    
count = 0
    
stk = []
for i in range(N-1, -1, -1):
    while stk:
        if heights[stk[-1]] < heights[i]:
            value[i] += value[stk.pop()] + 1
            continue
        break
    stk.append(i)
    count += value[i]
    
print(count)