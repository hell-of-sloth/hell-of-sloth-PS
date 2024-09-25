from sys import stdin

N = int(stdin.readline().strip())
L = map(int, stdin.readline().split())
T, P = map(int, stdin.readline().split())

T_cnt = 0
P_cnt = 0

for i in L:
    if i % T == 0:
        T_cnt += i // T
    else:
        T_cnt += i // T + 1

print(T_cnt)
print(N // P, N % P)