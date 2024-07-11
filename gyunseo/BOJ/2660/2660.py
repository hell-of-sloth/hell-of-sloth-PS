import sys
input = sys.stdin.readline
from collections import deque

def BFS(start):
    distance[start] = 1
    q = deque()
    q.append(start)
    
    while q:
        cur_node = q.popleft()
        for next_node in graph[cur_node]:
            if distance[next_node] > 0:
                continue
            distance[next_node] = distance[cur_node] + 1
            q.append(next_node)


if __name__ == "__main__":
    N = int(input().strip())
    graph = [[] for _ in range(N + 1)]
    distance = [0 for _ in range(N + 1)]
    ansList = []
    minStepsToTraverseAllNodes = int(1e9)
    while True:
        u, v = map(int, input().strip().split())
        if u == -1 and v == -1:
            break
        graph[u].append(v)
        graph[v].append(u)
    for start_node in range(1, N + 1):
        BFS(start_node)
        stepsToTraverseAllNodes = max(distance) - 1
        minStepsToTraverseAllNodes = min(minStepsToTraverseAllNodes, stepsToTraverseAllNodes)
        ansList.append((start_node, stepsToTraverseAllNodes))
        distance = [0 for _ in range(N + 1)]
    
    ansList = [*map(lambda x: x[0], filter(lambda x: x[1] == minStepsToTraverseAllNodes, ansList))]
    print(minStepsToTraverseAllNodes, len(ansList))
    print(" ".join(map(str, ansList)))
    # N이 0, 1일 때는 처리를 안 해줘도 되나..?