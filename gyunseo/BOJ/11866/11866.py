import sys
from collections import deque

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

N, K = map(int, input().rstrip().split())

circular_queue = deque([i for i in range(1, N + 1)])
# print(f"{circular_queue}\n")

print("<")
cnt = 1
while circular_queue:
    cur_item = circular_queue.popleft()
    if cnt == K:
        print(f"{cur_item}")
        if circular_queue:
            print(", ")
        cnt = 1
    else:
        circular_queue.append(cur_item)
        cnt += 1

print(">\n")
