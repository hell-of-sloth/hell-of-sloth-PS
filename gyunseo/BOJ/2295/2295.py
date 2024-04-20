import sys
from bisect import bisect_left, bisect_right

input = sys.stdin.readline

N = int(input().rstrip())
nums = []
for _ in range(N):
    nums.append(int(input().rstrip()))

V = set()
nums.sort()
for i in range(N):
    for j in range(N):
        V.add(nums[i] + nums[j])
V = list(V)
V.sort()
lenV = len(V)
for i in range(N - 1, -1, -1):
    for j in range(i - 1, -1, -1):
        t = nums[i] - nums[j]
        if bisect_right(V, t) - bisect_left(V, t) == 0:
            continue
        print(nums[i])
        exit(0)
