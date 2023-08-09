from collections import deque

ans = deque()

for i in range(int(input())):
    n = int(input())
    if n == 0:
        ans.pop()
    else:
        ans.append(n)
print(sum(ans))