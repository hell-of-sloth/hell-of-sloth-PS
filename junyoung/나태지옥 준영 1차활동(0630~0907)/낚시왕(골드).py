# 답은 맞는데 시간초과 걸리는 코드

import sys
from collections import deque

R, C, M = map(int, sys.stdin.readline().split())

sharks = deque()
matrix = [[0] * (C + 1) for _ in range(R + 1)]
shark_weight = 0

for _ in range(M):
    r, c, s, d, z = map(int, sys.stdin.readline().split())
    sharks.append((r, c, s, d, z))
    matrix[r][c] = (r, c, s, d, z)

def Shark_Move(r, c ,s, d):
    global R, C
    
    if d == 1 or d == 2:
        s = s % ((R - 1) * 2)
    else:
        s = s % ((C - 1) * 2)
    while s > 0:
        if d == 1:
            if r == 1:
                d = 2
                r += 1
            else:
                r -= 1
        elif d == 2:
            if r == R:
                d = 1
                r -= 1
            else:
                r += 1
        elif d == 3:
            if c == C:
                d = 4
                c -= 1
            else:
                c += 1
        elif d == 4:
            if c == 1:
                d = 3
                c += 1
            else:
                c -= 1
        s -= 1
    return r, c, d

for i in range(1, C+1):
    for j in range(1, R+1):
        if matrix[j][i]:
            caught_shark = matrix[j][i]
            matrix[j][i] = 0
            sharks.remove(caught_shark)
            shark_weight += caught_shark[4]
            break
    
    matrix = [[0] * (C + 1) for _ in range(R + 1)]
    new_sharks = deque()
    for j in range(len(sharks)):
        r, c, s, d, z = sharks.popleft()
        r, c, d = Shark_Move(r, c, s, d)
        if matrix[r][c] != 0:
            if matrix[r][c][4] < z:
                if matrix[r][c] in new_sharks:
                    new_sharks.remove(matrix[r][c])
                new_sharks.append((r, c, s, d, z))
                matrix[r][c] = (r, c, s, d, z)
        else:
            new_sharks.append((r, c, s, d, z))
            matrix[r][c] = (r, c, s, d, z)
    sharks = new_sharks    
    
print(shark_weight)