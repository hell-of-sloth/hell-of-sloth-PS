import sys

N, M = map(int, sys.stdin.readline().split())

trees = list(map(int, sys.stdin.readline().split()))
trees.sort()

start = 0
end = trees[-1]

while start <= end:
    mid = (start + end) // 2
    total = 0
    for i in range(N-1, -1, -1):
        cut_wood = trees[i] - mid
        if cut_wood >= 0:
            total += cut_wood
        else:
            break
    if total >= M:
        start = mid + 1
    else:
        end = mid - 1
        
print(start-1)