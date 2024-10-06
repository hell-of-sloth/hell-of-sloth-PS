import sys

N = int(sys.stdin.readline().strip())

max_grid = [0, 0, 0]
min_grid = [0, 0, 0]

for i in range(N):
    a, b, c = map(int, sys.stdin.readline().split())
    
    max_grid = [max(max_grid[0], max_grid[1]) + a, max(max_grid) + b, max(max_grid[1], max_grid[2]) + c]
    min_grid = [min(min_grid[0], min_grid[1]) + a, min(min_grid) + b, min(min_grid[1], min_grid[2]) + c]
    
print(max(max_grid), min(min_grid))