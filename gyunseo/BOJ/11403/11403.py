import sys

input = sys.stdin.readline

INF = int(1e9)

if __name__ == "__main__":
    N = int(input().rstrip())
    adjMat = [[*map(int, input().rstrip().split())] for i in range(N)]

    ans = [[0 for j in range(N)] for _ in range(N)]
    dist = [[INF for j in range(N)] for i in range(N)]
    for i in range(N):
        for j in range(N):
            if adjMat[i][j]:
                dist[i][j] = adjMat[i][j]
    for k in range(N):
        for i in range(N):
            for j in range(N):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
        # print(f"{k} 노드는 무조건 들러서, dist relaxation을 시킨 결과:")
        # print(dist)

    for i in range(N):
        for j in range(N):
            if dist[i][j] == INF:
                print(0, end=" ")
            else:
                print(1, end=" ")
        print()
