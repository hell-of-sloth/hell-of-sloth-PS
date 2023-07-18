import sys
from copy import deepcopy # 깊은 복사를 위한 모듈 불러오기

N = int(sys.stdin.readline().rstrip())

board = [] # 2048 리스트
max_num = [] # 최대값을 저장할 리스트

for i in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))
    
def Vertical_Up(B): # 위로 이동
    global N
    
    already = [] # 이미 합쳐진 좌표 저장
    
    for j in range(N):
        for i in range(1, N):
            if B[i][j] == 0: # 0 이면 넘어가욧
                continue
            while True:
                if i - 1 < 0:
                    break
                if B[i - 1][j] == 0: # 0 이면 이동
                    B[i - 1][j] = B[i][j]
                    B[i][j] = 0
                    i -= 1
                elif B[i - 1][j] == B[i][j]: # 같으면 합치기
                    if (i - 1, j) not in already: # 이미 합쳐진 좌표가 아니면
                        B[i - 1][j] *= 2
                        B[i][j] = 0
                        already.append((i - 1, j))
                        break
                    else: # 이미 합쳐진 좌표면 넘어가욧
                        break
                else:
                    break
    # print('\nUP\n')               # 디버깅을 위한 흔적
    # for i in range(N):
    #     for j in range(N):
    #         print(B[i][j], end = ' ')
    #     print('\n')       
    return B

def Vertical_Down(B): # 아래로 이동
    global N
    
    already = []
    
    for j in range(N):
        for i in range(N-2, -1, -1):
            if B[i][j] == 0:
                continue
            while True:
                if i + 1 > N - 1:
                    break
                if B[i + 1][j] == 0:
                    B[i + 1][j] = B[i][j]
                    B[i][j] = 0
                    i += 1
                elif B[i + 1][j] == B[i][j]:
                    if (i + 1, j) not in already:
                        B[i + 1][j] *= 2
                        B[i][j] = 0
                        already.append((i + 1, j))
                        break
                    else:
                        break
                else:
                    break
    # print('\nDOWN\n') 
    # for i in range(N):
    #     for j in range(N):
    #         print(B[i][j], end = ' ')
    #     print('\n')     
    return B

def Horizontal_Left(B): # 왼쪽으로 이동
    global N
    
    already = []
    
    for i in range(N):
        for j in range(1, N):
            if B[i][j] == 0:
                continue
            while True:
                if j - 1 < 0:
                    break
                if B[i][j - 1] == 0:
                    B[i][j - 1] = B[i][j]
                    B[i][j] = 0
                    j -= 1
                elif B[i][j - 1] == B[i][j]:
                    if (i, j - 1) not in already:
                        B[i][j - 1] *= 2
                        B[i][j] = 0
                        already.append((i, j - 1))
                        break
                    else:
                        break
                else:
                    break
    # print('\nLEFT\n') 
    # for i in range(N):
    #     for j in range(N):
    #         print(B[i][j], end = ' ')
    #     print('\n')   
    return B

def Horizontal_Right(B): # 오른쪽으로 이동
    global N
    
    already = []
    
    for i in range(N):
        for j in range(N-2, -1, -1):
            if B[i][j] == 0:
                continue
            while True:
                if j + 1 > N - 1:
                    break
                if B[i][j + 1] == 0:
                    B[i][j + 1] = B[i][j]
                    B[i][j] = 0
                    j += 1
                elif B[i][j + 1] == B[i][j]:
                    if (i, j + 1) not in already:
                        B[i][j + 1] *= 2
                        B[i][j] = 0
                        already.append((i, j + 1))
                        break
                    else:
                        break
                else:
                    break
    # print('\nRIGHT\n') 
    # for i in range(N):
    #     for j in range(N):
    #         print(B[i][j], end = ' ')
    #     print('\n')   
    return B
        
def YeeGongSaPal(B, count): # 2048 메인 함수
    global N, max_num
    
    if count == 5: # 5번 이동했으면
        big = 0
        for i in range(N):
            for j in range(N):
                if B[i][j] > big:
                    big = B[i][j]
        max_num.append(big) # 최대값 저장
        return
    else: # 5번 이동 안했으면 4방향으로 이동, 재귀함수
        count += 1
        new_board = deepcopy(B)
        YeeGongSaPal(Vertical_Up(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Vertical_Down(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Horizontal_Left(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Horizontal_Right(new_board), count)
        
# def mugen(B):                    # 무한 츠쿠요미
#     global N
    
#     new_board = deepcopy(B)
    
#     while True:
#         com = input()
    
#         if com == 'u':
#             Vertical_Up(new_board)
#         elif com == 'd':
#             Vertical_Down(new_board)
#         elif com == 'l':
#             Horizontal_Left(new_board)
#         elif com == 'r':
#             Horizontal_Right(new_board)
#         elif com == 'q':
#             break

# mugen(board)
YeeGongSaPal(board, 0)
print(max(max_num)) # 최대값 출력