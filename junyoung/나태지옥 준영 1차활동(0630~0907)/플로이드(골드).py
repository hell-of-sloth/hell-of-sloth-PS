import sys # 쓰면 50ms 빨라짐

PRINT = sys.stdout.write

n = int(input()) # 도시의 개수

m = int(input()) # 버스의 개수

graph = [[float('inf')] * (n + 1) for _ in range(n + 1)] # 그래프

for i in range(m):
    a, b, c = map(int, sys.stdin.readline().split()) # a에서 b로 가는 비용이 c
    if graph[a][b] > c:
        graph[a][b] = c                 # 버스의 비용

def Floyd(): # 플로이드 워셜 알고리즘
    global graph
    
    for k in range(1, n + 1):
        for i in range(1, n + 1):
            if k == i: continue # 자기 자신으로 가는 비용은 0
            for j in range(1, n + 1):
                if k == j or i == j: continue # 자기 자신으로 가는 비용은 0
                if graph[i][j] > graph[i][k] + graph[k][j]:
                    graph[i][j] = graph[i][k] + graph[k][j]

# 메인
Floyd()

for i in range(1, n + 1):
    for j in range(1, n + 1):
        if graph[i][j] == float('inf'):
            PRINT(str(0) + ' ')
        else:
            PRINT(str(graph[i][j]) + ' ')
    print()