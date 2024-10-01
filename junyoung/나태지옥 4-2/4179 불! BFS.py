# 체감 난이도 6/10, BFS를 사용해서 풀었음, 3차원 배열에 시간 정보 저장 1900ms 정도 -> 좀 오래걸림
# 시간 빠른 코드(900ms)를 보니까 큐를 하나만 사용해서 풂, 불과 지훈이를 나누는 항목을 더 둚 (a, b, 'F', 0) 이런식
# visited도 한개로 통합 어차피 불이지나면 못 가니까 -> 천젠데?, 순서를 불이 먼저로 하면 가능

import sys
from collections import deque

R, C = map(int, sys.stdin.readline().split())

graph = []
j_queue = deque()
f_queue = deque()

# 3차원 배열
matrix = [[[0] * 2 for _ in range(C)] for _ in range(R)]

for i in range(R):
    L = list(sys.stdin.readline().strip())
    for j in range(C):
        if L[j] == 'F':
            f_queue.append((i, j))
            matrix[i][j][1] = 1
        elif L[j] == 'J':
            j_queue.append((i, j))
            matrix[i][j][0] = 1
    graph.append(L)

def BFS():
    result = float('inf')

    direc = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    
    # 불 먼저 퍼뜨리기
    while f_queue:
        x, y = f_queue.popleft()
        for dx, dy in direc:
            nx, ny = x + dx, y + dy
            if 0 <= nx < R and 0 <= ny < C and matrix[nx][ny][1] == 0 and graph[nx][ny] != '#':
                matrix[nx][ny][1] = matrix[x][y][1] + 1
                f_queue.append((nx, ny))
    
    # print("불 입니다.")
    # for i in range(R):
    #     for j in range(C):
    #         print(matrix[i][j][1], end = ' ')
    #     print()
    # print()
    
    # 지훈이 이동, 불의 시간이 먼저갔으면 못 감
    while j_queue:
        x, y = j_queue.popleft()
        if x == 0 or x == R - 1 or y == 0 or y == C - 1:
            result = matrix[x][y][0]
            return result
        for dx, dy in direc:
            nx, ny = x + dx, y + dy
            if 0 <= nx < R and 0 <= ny < C and matrix[nx][ny][0] == 0 and graph[nx][ny] == '.':
                if matrix[x][y][0] + 1 < matrix[nx][ny][1] or matrix[nx][ny][1] == 0:
                    matrix[nx][ny][0] = matrix[x][y][0] + 1
                    j_queue.append((nx, ny))
                    # if nx == 0 or nx == R - 1 or ny == 0 or ny == C - 1:
                    #     result = matrix[nx][ny][0]
                    #     return result
                        
                    
    # print("지훈이 입니다.")
    # for i in range(R):
    #     for j in range(C):
    #         print(matrix[i][j][0], end = ' ')
    #     print()
    # print()
    
    return "IMPOSSIBLE"
            
print(BFS())
