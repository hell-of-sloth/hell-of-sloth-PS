import sys
from copy import deepcopy

N = int(sys.stdin.readline().rstrip())

board = []
max_num = []

for i in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))
    
def Vertical_Up(B):
    global N
    
    already = []
    
    for j in range(N):
        for i in range(1, N):
            if B[i][j] == 0:
                continue
            while True:
                if i - 1 < 0:
                    break
                if B[i - 1][j] == 0:
                    B[i - 1][j] = B[i][j]
                    B[i][j] = 0
                    i -= 1
                elif B[i - 1][j] == B[i][j]:
                    if (i - 1, j) not in already:
                        B[i - 1][j] *= 2
                        B[i][j] = 0
                        already.append((i - 1, j))
                        break
                    else:
                        break
                else:
                    break
    # print('\nUP\n') 
    # for i in range(N):
    #     for j in range(N):
    #         print(B[i][j], end = ' ')
    #     print('\n')       
    return B

def Vertical_Down(B):
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

def Horizontal_Left(B):
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

def Horizontal_Right(B):
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
        
def YeeGongSaPal(B, count):
    global N, max_num
    
    if count == 5:
        big = 0
        for i in range(N):
            for j in range(N):
                if B[i][j] > big:
                    big = B[i][j]
        max_num.append(big)
        return
    else:
        count += 1
        new_board = deepcopy(B)
        YeeGongSaPal(Vertical_Up(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Vertical_Down(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Horizontal_Left(new_board), count)
        new_board = deepcopy(B)
        YeeGongSaPal(Horizontal_Right(new_board), count)
        
# def mugen(B):
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
print(max(max_num))
        