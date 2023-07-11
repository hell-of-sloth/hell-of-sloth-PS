import sys
from collections import deque

N, M = map(int, input().split())

who_is_first = [[] for _ in range(N+1)]
in_demention = [0 for _ in range(N+1)]

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    who_is_first[b].append(a)
    in_demention[a] += 1
    
def Topology_Sort():
    global N, who_is_first, in_demention
    
    queue = deque()
    result = []
    for i in range(1, N+1):
        if in_demention[i] == 0:
            queue.append(i)
    while queue:
        now = queue.popleft()
        result.append(now)
        for i in who_is_first[now]:
            in_demention[i] -= 1
            if in_demention[i] == 0:
                queue.append(i)
    result.reverse()
    return result

print(*Topology_Sort())