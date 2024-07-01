import sys
from collections import deque

input = sys.stdin.readline


class Solution:
    def __init__(self):
        pass

    def outOfBound(self, cur_i, cur_j):
        if cur_i < 0 or cur_i >= n:
            return True
        if cur_j < 0 or cur_j >= m:
            return True
        return False

    def bfs(self, start_pos):
        ret_area = 0
        q = deque()
        start_i, start_j = start_pos
        dist[start_i][start_j] = 1
        q.append(start_pos)
        ret_area += 1
        while q:
            cur_i, cur_j = q.popleft()
            # print(f"cur pos: {cur_i, cur_j}")
            for di, dj in zip(deltaI, deltaJ):
                next_i, next_j = cur_i + di, cur_j + dj

                if self.outOfBound(next_i, next_j):
                    continue
                if matrix[next_i][next_j] == 0:
                    continue
                if dist[next_i][next_j] > 0:
                    continue

                dist[next_i][next_j] = dist[cur_i][cur_j] + 1
                q.append((next_i, next_j))
                ret_area += 1
                # print(f"{next_i, next_j} appended for next pos")

        return ret_area

    def floodFill(self):
        num_pic = 0
        max_area = 0
        for start_i, start_j in startPosCandList:
            if dist[start_i][start_j] > 0:
                continue
            num_pic += 1
            max_area_cand = self.bfs((start_i, start_j))
            if max_area_cand > max_area:
                max_area = max_area_cand

        for i in range(n):
            for j in range(m):
                if dist[i][j] > max_area:
                    max_area = dist[i][j]
        # print(matrix)
        # print(startPosCandList)
        # for i in range(n):
        #     for j in range(m):
        #         print(dist[i][j], end=" ")
        #     print()
        print(num_pic)
        print(max_area)


if __name__ == "__main__":
    n, m = map(int, input().strip().split())
    matrix = [[*map(int, input().strip().split())] for _ in range(n)]
    dist = [[0 for __ in range(m)] for _ in range(n)]
    deltaI = (1, -1, 0, 0)
    deltaJ = (0, 0, 1, -1)
    startPosCandList = []
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == 1:
                startPosCandList.append((i, j))
    sol = Solution()
    sol.floodFill()
