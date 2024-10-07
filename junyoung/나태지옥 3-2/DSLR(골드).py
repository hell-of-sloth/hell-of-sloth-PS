import sys
from collections import deque

T = int(sys.stdin.readline().rstrip())

def DSLR(A, B): # 드럽게 오래걸리네
    queue = deque()
    queue.append((A, ''))
    visited = set() # 중복 검사
    while queue:
        num, command = queue.popleft()
        if num == B: # 목표값에 도달하면
            return command
        if num in visited: # 이미 방문한 곳이면
            continue
        visited.add(num)
        D = (num * 2) % 10000
        S = num - 1 if num != 0 else 9999
        L = (num % 1000) * 10 + num // 1000
        R = (num % 10) * 1000 + num // 10
        
        if D not in visited:
            queue.append((D, command + 'D'))
        if S not in visited:
            queue.append((S, command + 'S'))
        if L not in visited:
            queue.append((L, command + 'L'))
        if R not in visited:
            queue.append((R, command + 'R'))
        
for _ in range(T):
    A, B = map(int, sys.stdin.readline().split())
    sys.stdout.write(DSLR(A, B) + '\n')