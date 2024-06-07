import sys
from collections import deque
import heapq

input = sys.stdin.readline

di = (-1, 1, 0, 0)
dj = (0, 0, -1, 1)

INF = int(1e9)


def OOB(i, j):
    if i < 0 or i >= N:
        return True
    if j < 0 or j >= N:
        return True
    return False


def dijkstra():
    isVisited = [[False for __ in range(N)] for _ in range(N)]
    dp = [[INF for __ in range(N)] for _ in range(N)]
    dp[0][0] = board[0][0]
    hq = [(dp[0][0], 0, 0)]
    heapq.heapify(hq)
    isVisited[0][0] = True
    while hq:
        cdist, ci, cj = heapq.heappop(hq)
        if cdist > dp[ci][cj]:
            continue
        for k in range(4):
            ni, nj = ci + di[k], cj + dj[k]
            if OOB(ni, nj):
                continue
            if isVisited[ni][nj]:
                continue
            tmp_dist = dp[ci][cj] + board[ni][nj]
            if tmp_dist < dp[ni][nj]:
                dp[ni][nj] = tmp_dist
                heapq.heappush(hq, (tmp_dist, ni, nj))
    return dp[N - 1][N - 1]


if __name__ == "__main__":
    tc_idx = 1
    while True:
        N = int(input().rstrip())
        if N == 0:
            break
        board = [[*map(int, input().rstrip().split())] for _ in range(N)]
        print(f"Problem {tc_idx}: {dijkstra()}")
        tc_idx += 1
