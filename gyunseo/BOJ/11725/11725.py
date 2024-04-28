import sys
from collections import deque

input = sys.stdin.readline


if __name__ == "__main__":
    N = int(input().rstrip())
    graph = [[] for _ in range(N + 1)]
    isVisited = [False for _ in range(N + 1)]
    pre = [0 for _ in range(N + 1)]
    for _ in range(N - 1):
        u, v = map(int, input().rstrip().split())
        graph[u].append(v)
        graph[v].append(u)
    q = deque()
    isVisited[1] = True
    q.append(1)
    while q:
        cur = q.popleft()
        for nv in graph[cur]:
            if isVisited[nv]:
                continue
            isVisited[nv] = True
            pre[nv] = cur
            q.append(nv)
    for i in range(2, N + 1):
        print(pre[i])
