from collections import deque

m,n = map(int, input().split())

chair = deque([i for i in range(1, m+1)])
ans = []
while chair:
    for _ in range(n-1):
        chair.append(chair.popleft())
    ans.append(str(chair.popleft()))

print('<'+', '.join(ans)+'>')
