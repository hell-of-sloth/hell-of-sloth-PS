import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
MAX = 100000
graph_f = [[] for _ in range(MAX + 1)]
graph_r = [[] for _ in range(MAX + 1)]

for _ in range(m):
    u, v = map(int, input().rstrip().split())
    graph_f[u].append(v)
    graph_r[v].append(u)

S, T = map(int, input().rstrip().split())

is_visited1 = [False for _ in range(MAX + 1)]
is_visited2 = [False for _ in range(MAX + 1)]
is_visited3 = [False for _ in range(MAX + 1)]
is_visited4 = [False for _ in range(MAX + 1)]


dq = deque()


def bfs(start, is_visited, graph):
    dq.append(start)
    is_visited[start] = True

    while dq:
        cur = dq.popleft()

        for nv in graph[cur]:
            if is_visited[nv]:
                continue

            is_visited[nv] = True
            dq.append(nv)


while dq:
    dq.popleft()
# T까지 까지 가는 길이 무조건 보장이 돼 있다
is_visited1[T] = True
bfs(S, is_visited1, graph_f)
# print(f"is_visited1: {is_visited1}")

while dq:
    dq.popleft()
# S까지 까지 가는 길이 무조건 보장이 돼 있다
is_visited2[S] = True
bfs(T, is_visited2, graph_f)
# print(f"is_visited2: {is_visited2}")

while dq:
    dq.popleft()
bfs(S, is_visited3, graph_r)
# print(f"is_visited3: {is_visited3}")

while dq:
    dq.popleft()
bfs(T, is_visited4, graph_r)
# print(f"is_visited4: {is_visited4}")

ans = 0
for i in range(1, n + 1):
    if i == S or i == T:
        continue

    if is_visited1[i] and is_visited2[i] and is_visited3[i] and is_visited4[i]:
        ans += 1

print(ans)
