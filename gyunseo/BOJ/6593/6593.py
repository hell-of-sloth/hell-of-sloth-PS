import sys
from collections import deque

input = sys.stdin.readline


def OOB(cur_k, cur_i, cur_j):
    if cur_k < 0 or cur_k >= L:
        return True
    if cur_i < 0 or cur_i >= R:
        return True
    if cur_j < 0 or cur_j >= C:
        return True

    return False


def BFS(start_pos):
    q = deque()
    q.append(start_pos)
    start_k, start_i, start_j = start_pos
    distance[start_k][start_i][start_j] = 1
    while q:
        cur_k, cur_i, cur_j = q.popleft()
        for delta_k, delta_i, delta_j in zip(DK, DI, DJ):
            next_k, next_i, next_j = cur_k + delta_k, cur_i + delta_i, cur_j + delta_j
            if OOB(next_k, next_i, next_j):
                continue
            if board[next_k][next_i][next_j] == 1:
                continue
            if distance[next_k][next_i][next_j] > 0:
                continue

            distance[next_k][next_i][next_j] = distance[cur_k][cur_i][cur_j] + 1
            q.append((next_k, next_i, next_j))


if __name__ == "__main__":
    # 동서남북상하
    DK = [1, -1, 0, 0, 0, 0]
    DI = [0, 0, 1, -1, 0, 0]
    DJ = [0, 0, 0, 0, 1, -1]

    while True:
        L, R, C = map(int, input().strip().split())
        if L == 0 and R == 0 and C == 0:
            break
        startPos = endPos = None
        distance = [[[0 for ___ in range(C)] for __ in range(R)] for _ in range(L)]
        board = [[[0 for ___ in range(C)] for __ in range(R)] for _ in range(L)]
        for k in range(L):
            for i in range(R):
                line = input().strip()
                for j in range(C):
                    if line[j] == "S":
                        board[k][i][j] = 0
                        startPos = (k, i, j)
                    elif line[j] == "E":
                        board[k][i][j] = 0
                        endPos = (k, i, j)
                    elif line[j] == ".":
                        board[k][i][j] = 0
                    elif line[j] == "#":
                        board[k][i][j] = 1

            # read blank new line
            input().strip()
        # print("board:", board)
        BFS(startPos)
        # print(distance)
        if distance[endPos[0]][endPos[1]][endPos[2]]:
            print(
                f"Escaped in {distance[endPos[0]][endPos[1]][endPos[2]] - 1} minute(s)."
            )
        else:
            print("Trapped!")
