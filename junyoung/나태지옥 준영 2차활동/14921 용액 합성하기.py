import sys

N = int(sys.stdin.readline().strip())

liquids = list(map(int, sys.stdin.readline().split()))

l = 0
r = N-1
min_val = float('inf')

while l < r:
    sum_val = liquids[l] + liquids[r]

    if abs(sum_val) < abs(min_val):
        min_val = sum_val
        if min_val == 0:
            break
    
    if sum_val < 0:
        l += 1
    elif sum_val > 0:
        r -= 1

print(min_val)