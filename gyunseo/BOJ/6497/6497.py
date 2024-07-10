import sys
from heapq import heapify, heappush, heappop

input = sys.stdin.readline

if __name__ == "__main__":
    while True:
        # num of nodes, num of edges
        M, N = map(int, input().strip().split())
        if M == 0 and N == 0:
            break
        graph = [[] for _ in range(M)]
        minSumWeight = 0
        total = 0
        isSelected = set()
        for _ in range(N):
            x, y, z = map(int, input().strip().split())
            total += z
            graph[x].append((z, y))
            graph[y].append((z, x))
        # 0에서 시작
        isSelected.add(0)
        edgeList = [(w, 0, v) for w, v in graph[0]]
        heapify(edgeList)
        while edgeList:
            w, src, dst = heappop(edgeList)
            if dst in isSelected:
                continue
            isSelected.add(dst)
            minSumWeight += w
            for w, v in graph[dst]:
                heappush(edgeList, (w, dst, v))
            if len(isSelected) == M:
                break
        print(total - minSumWeight)
