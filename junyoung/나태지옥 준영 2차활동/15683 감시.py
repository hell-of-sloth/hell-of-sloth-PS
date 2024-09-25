# 설명이 길어서 따로 링크 첨부
# https://www.acmicpc.net/problem/15683

import sys

N, M = map(int, sys.stdin.readline().split())
samusil = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
cctv_pos = [] # (cctv 종류, x좌표, y좌표) 저장
result = 1000

# cctv의 위치 저장
for i in range(N):
    for j in range(M):
        if 0 < samusil[i][j] < 6:
            cctv_pos.append((samusil[i][j], i, j))            

# cctv의 방향에 따라 감시할 수 있는 영역을 -1로 표시
def cctv_N(x, y, temp_samusil): # 북쪽
    for i in range(x, -1, -1):
        if temp_samusil[i][y] == 6:
            break
        elif temp_samusil[i][y] == 0:
            temp_samusil[i][y] = -1
        else:
            continue
        
    return temp_samusil
    
def cctv_E(x, y, temp_samusil): # 동쪽
    for j in range(y, M):
        if temp_samusil[x][j] == 6:
            break
        elif temp_samusil[x][j] == 0:
            temp_samusil[x][j] = -1
        else:
            continue
        
    return temp_samusil
    
def cctv_S(x, y, temp_samusil): # 남쪽
    for i in range(x, N):
        if temp_samusil[i][y] == 6:
            break
        elif temp_samusil[i][y] == 0:
            temp_samusil[i][y] = -1
        else:
            continue
    
    return temp_samusil


def cctv_W(x, y, temp_samusil): # 서쪽
    for j in range(y, -1, -1):
        if temp_samusil[x][j] == 6:
            break
        elif temp_samusil[x][j] == 0:
            temp_samusil[x][j] = -1
        else:
            continue
        
    return temp_samusil

# cctv의 종류와 방향에 따라 감시할 수 있는 영역을 -1로 표시(위의 4개 함수 이용), ☆slicing deep copy☆
def cctv_operation(kind, x, y, direction, samusil):
    temp_samusil = [i[:] for i in samusil]
    if kind == 1:
        if direction == 0:
            return cctv_N(x, y, temp_samusil)
        
        elif direction == 1:
            return cctv_E(x, y, temp_samusil)
        
        elif direction == 2:
            return cctv_S(x, y, temp_samusil)
        
        elif direction == 3:
            return cctv_W(x, y, temp_samusil)
        
    elif kind == 2:
        if direction == 0:
            temp_samusil = cctv_N(x, y, temp_samusil)   
            return cctv_S(x, y, temp_samusil)
        
        elif direction == 1:
            temp_samusil = cctv_E(x, y, temp_samusil)
            return cctv_W(x, y, temp_samusil)
        
    elif kind == 3:
        if direction == 0:
            temp_samusil = cctv_N(x, y, temp_samusil)   
            return cctv_E(x, y, temp_samusil)
        
        elif direction == 1:
            temp_samusil = cctv_E(x, y, temp_samusil)
            return cctv_S(x, y, temp_samusil)
        
        elif direction == 2:
            temp_samusil = cctv_S(x, y, temp_samusil)
            return cctv_W(x, y, temp_samusil)
        
        elif direction == 3:
            temp_samusil = cctv_W(x, y, temp_samusil)
            return cctv_N(x, y, temp_samusil)
        
    elif kind == 4:
        if direction == 0:
            temp_samusil = cctv_N(x, y, temp_samusil)   
            temp_samusil = cctv_E(x, y, temp_samusil)
            return cctv_S(x, y, temp_samusil)
        
        elif direction == 1:
            temp_samusil = cctv_E(x, y, temp_samusil)
            temp_samusil = cctv_S(x, y, temp_samusil)
            return cctv_W(x, y, temp_samusil)
        
        elif direction == 2:
            temp_samusil = cctv_S(x, y, temp_samusil)
            temp_samusil = cctv_W(x, y, temp_samusil)
            return cctv_N(x, y, temp_samusil)
        
        elif direction == 3:
            temp_samusil = cctv_W(x, y, temp_samusil)
            temp_samusil = cctv_N(x, y, temp_samusil)
            return cctv_E(x, y, temp_samusil)
        
    elif kind == 5:
        temp_samusil = cctv_N(x, y, temp_samusil)   
        temp_samusil = cctv_E(x, y, temp_samusil)
        temp_samusil = cctv_S(x, y, temp_samusil)
        return cctv_W(x, y, temp_samusil)

# 백트랙킹 이용
def backtracking(temp_samusil, cctv_pos):
    global result
    
    temp_cctv_pos = cctv_pos[:]
    
    # 모든 cctv의 방향을 정했을 때, 종료 조건
    if len(temp_cctv_pos) == 0:
        temp_result = 0
        for i in range(N):
            for j in range(M):
                if temp_samusil[i][j] == 0:
                    temp_result += 1
                    
        result = min(result, temp_result)
        return
    
    kind, x, y = temp_cctv_pos.pop()
    
    if kind == 1:
        for i in range(4):
            another_samusil = cctv_operation(kind, x, y, i, temp_samusil)
            backtracking(another_samusil, temp_cctv_pos)
    
    elif kind == 2:
        for i in range(2):
            another_samusil = cctv_operation(kind, x, y, i, temp_samusil)
            backtracking(another_samusil, temp_cctv_pos)
            
    elif kind == 3:
        for i in range(4):
            another_samusil = cctv_operation(kind, x, y, i, temp_samusil)
            backtracking(another_samusil, temp_cctv_pos)
            
    elif kind == 4:
        for i in range(4):
            another_samusil = cctv_operation(kind, x, y, i, temp_samusil)
            backtracking(another_samusil, temp_cctv_pos)
    
    elif kind == 5:
        another_samusil = cctv_operation(kind, x, y, 0, temp_samusil)
        backtracking(another_samusil, temp_cctv_pos)

# 디버깅용 맵 출력 함수
# def print_map(samusil):
#     for i in range(N):
#         for j in range(M):
#             print(samusil[i][j], end=' ')
#         print()
#     print()

backtracking(samusil, cctv_pos)

print(result)