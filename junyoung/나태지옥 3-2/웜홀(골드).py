import sys

TC = int(sys.stdin.readline().rstrip())

#벨만포드 알고리즘
def bellman_ford(start): # 음수 사이클이 존재하면 True, 아니면 False
    distance[start] = 0
    for i in range(1, N+1):
        for j in range(1, N + 1):
            for node, cost in graph[j]:
                if distance[node] > distance[j] + cost:
                    distance[node] = distance[j] + cost
                    if i == N:
                        return True
    return False


for _ in range(TC):
    
    N, M, W = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(N + 1)]
    distance = [1000001] * (N + 1)
    for _ in range(M):
        S, E, T = map(int, sys.stdin.readline().split())
        graph[S].append((E, T))
        graph[E].append((S, T))
    for _ in range(W):
        S, E, T = map(int, sys.stdin.readline().split())
        graph[S].append((E, -T))
    result = bellman_ford(1)
    if result:
        print('YES')
    else:
        print('NO')
    