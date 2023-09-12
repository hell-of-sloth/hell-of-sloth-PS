import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

board = []

for i in range(N):
    board.append(list(sys.stdin.readline().rstrip()))
    
for i in  range(N):
    for j in range(M):
        if board[i][j] == 'R': # 빨간구슬 위치
            rx, ry = j, i 
        elif board[i][j] == 'B': # 파란구슬 위치
            bx, by = j, i

def BFS_Bead(rx, ry, bx, by):
    # 상 하 좌 우로 탐색
    direction = [[0, -1], [0, 1], [-1, 0], [1, 0]]
    
    queue = deque()
    queue.append((rx, ry, bx, by))
    visited = [] # 방문여부를 판단하기 위한 리스트
    visited.append((rx, ry, bx, by))
    count = 0
    while queue:
        for i in range(len(queue)):
            rx, ry, bx, by = queue.popleft()
            
            if count > 10: # 움직인 횟수가 10회 초과면 -1 출력
                return -1
            if board[ry][rx] == 'O': # 현재 빨간 구슬의 위치가 구멍이라면 count출력
                return count
            for dx, dy in direction: # 4방향 탐색
                # 빨갱이
                new_rx, new_ry = rx, ry # 기존위치 보존
                while True: # '#'일 때까지 혹은 구멍일 때까지 움직임
                    new_rx += dx
                    new_ry += dy
                    if board[new_ry][new_rx] == '#': # 벽인 경우 왔던 방향 그대로 한칸 뒤로 이동
                        new_rx -= dx
                        new_ry -= dy
                        break
                    if board[new_ry][new_rx] == 'O':
                        break
                
                # 파랭이
                new_bx, new_by = bx, by # 기존위치 보존
                while True: # #일 때까지 혹은 구멍일 때까지 움직임
                    new_bx += dx
                    new_by += dy
                    if board[new_by][new_bx] == '#': # 벽인 경우 왔던 방향 그대로 한칸 뒤로 이동
                        new_bx -= dx
                        new_by -= dy
                        break
                    if board[new_by][new_bx] == 'O':
                        break
                    
                if board[new_by][new_bx] == 'O': # 파란구슬이 먼저 구멍에 들어가거나 동시에 들어가면 안됨 따라서 이 경우 무시
                    continue
                
                if new_rx == new_bx and new_ry == new_by: # 두 구슬의 위치가 같다면
                    if abs(new_rx - rx) + abs(new_ry - ry) > abs(new_bx - bx) + abs(new_by - by): # 이동거리 구하기 / 더 많이 이동한 구슬이 더 늦게 이동한 구슬이므로 한 칸 뒤로 이동
                        new_rx -= dx
                        new_ry -= dy
                    else:
                        new_bx -= dx
                        new_by -= dy
                if (new_rx, new_ry, new_bx, new_by) not in visited: # 방문 확인 후 큐에 넣기
                    queue.append((new_rx, new_ry, new_bx, new_by))
                    visited.append((new_rx, new_ry, new_bx, new_by))
        count += 1
    return -1 # 10회 전에 못 들어가도 -1 출력

print(BFS_Bead(rx, ry, bx, by))
 
