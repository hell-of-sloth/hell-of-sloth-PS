import sys
import heapq

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())

hq = []
for _ in range(N):
    x = int(input().rstrip())
    if x == 0:
        if hq:
            print(f"{-heapq.heappop(hq)}\n")
            continue
        print("0\n")

    heapq.heappush(hq, -x)
