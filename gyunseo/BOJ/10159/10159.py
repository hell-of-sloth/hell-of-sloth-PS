import sys

input = sys.stdin.readline


def DFS(cur_node):

    for next_node in graph[cur_node]:
        if isVisited[next_node]:
            continue

        isVisited[next_node] = True
        DFS(next_node)


def reversedDFS(cur_node):
    for next_node in reversedGraph[cur_node]:
        if reversedIsVisited[next_node]:
            continue

        reversedIsVisited[next_node] = True
        reversedDFS(next_node)


def resetVisitedLists():
    for i in range(N + 1):
        isVisited[i] = False
        reversedIsVisited[i] = False


if __name__ == "__main__":
    N = int(input().strip())
    M = int(input().strip())
    # graph[u].append(v) -> u가 v보다 크다
    graph = [[] for _ in range(N + 1)]
    isVisited = [False for _ in range(N + 1)]
    ans = [0 for _ in range(N + 1)]
    # graph[v].append(u) -> v가 u보다 작다를 저장하는 리스트
    reversedGraph = [[] for _ in range(N + 1)]
    reversedIsVisited = [False for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, input().strip().split())
        graph[u].append(v)
        reversedGraph[v].append(u)

    # O(N * (N + M + 2N))
    for i in range(1, N + 1):
        isVisited[i] = True
        DFS(i)
        reversedIsVisited[i] = True
        reversedDFS(i)
        for j in range(1, N + 1):
            if not isVisited[j] and not reversedIsVisited[j]:
                ans[i] += 1
        resetVisitedLists()

    for i in range(1, N + 1):
        print(ans[i])
