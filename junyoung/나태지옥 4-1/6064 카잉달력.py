import sys

T = int(sys.stdin.readline().strip())

for _ in range(T):
    M, N, x, y = map(int, sys.stdin.readline().strip().split())
    x -= 1
    y -= 1
    k = x
    while k < M*N:
        if k % N == y:
            print(k+1)
            break
        k += M
    else:
        print(-1)
        
# ????