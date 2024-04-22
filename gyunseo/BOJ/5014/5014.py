import sys
from collections import deque

input = sys.stdin.readline


def OOB(x):
    if x < 1 or x > F:
        return True
    return False


def bfs(start):
    q = deque()
    dist[start] = 1
    q.append(start)
    while q:
        cx = q.popleft()
        for dx in DX:
            nx = cx + dx
            if OOB(nx):
                continue
            if dist[nx] > 0:
                continue
            dist[nx] = dist[cx] + 1
            q.append(nx)


if __name__ == "__main__":
    F, S, G, U, D = map(int, input().rstrip().split())
    dist = [0 for _ in range(F + 1)]
    DX = [U, -D]
    DX = [*filter(lambda x: x != 0, DX)]
    bfs(S)
    if dist[G] > 0:
        print(dist[G] - 1)
    else:
        print("use the stairs")
