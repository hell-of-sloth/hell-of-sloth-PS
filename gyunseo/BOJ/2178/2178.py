import sys
from collections import deque

input = sys.stdin.readline
print = sys.stdout.write
MAX_NUM = 100
N, M = map(int, input().rstrip().split())
board = []
is_visited = [[False for _ in range(MAX_NUM)] for __ in range(MAX_NUM)]
di = [-1, 1, 0, 0]
dj = [0, 0, -1, 1]
for _ in range(N):
    board.append(list(map(int, input().rstrip())))


def OOB(i, j):
    if i < 0 or i >= N:
        return True
    if j < 0 or j >= M:
        return True
    return False


q = deque()
q.append((0, 0, 1))
while q:
    cur_i, cur_j, cur_cnt = q.popleft()
    if is_visited[cur_i][cur_j]:
        continue
    is_visited[cur_i][cur_j] = True
    if cur_i == N - 1 and cur_j == M - 1:
        print(f"{cur_cnt}\n")
        break
    for k in range(4):
        next_i, next_j = cur_i + di[k], cur_j + dj[k]
        if OOB(next_i, next_j):
            continue
        if is_visited[next_i][next_j]:
            continue
        if board[next_i][next_j] == 0:
            continue
        q.append((next_i, next_j, cur_cnt + 1))
