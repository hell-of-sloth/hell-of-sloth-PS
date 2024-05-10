import sys

N = int(sys.stdin.readline().strip())
G = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

def floyd():
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if G[i][k] and G[k][j]:
                    G[i][j] = 1

floyd()

for i in range(N):
    print(' '.join(map(str, G[i])))