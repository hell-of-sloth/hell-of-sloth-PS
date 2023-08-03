import sys

sys.setrecursionlimit(10 ** 8)

def Catch_Shark(j): # j열에서 가장 가까운 상어를 잡아서 크기를 반환
    global board
    
    for i in range(R):
        if board[i][j]:
            x = board[i][j][2]
            board[i][j] = 0
            return x
    return 0


def Shark_Move(): # 상어들을 이동시킴
    global board  # board[i][j] 안에는 (s,d,z)의 값이 들어있음. 상어가 없는 경우엔 0이 들어있음
    
    new_board = [[0 for _ in range(C)] for _ in range(R)]  # 상어들의 새 위치를 담을 공간
    
    for i in range(R):
        for j in range(C):
            if board[i][j]: # 상어가 있다면
                # 이 상어의 다음 위치는
                ni, nj, nd = Next_Loc(i, j, board[i][j][0], board[i][j][1])
                if new_board[ni][nj]: # 이미 상어가 있다면
                    new_board[ni][nj] = max(
                        new_board[ni][nj],
                        (board[i][j][0], nd, board[i][j][2]),
                        key=lambda x: x[2],
                    ) # 크기가 큰 상어가 들어가도록, 나머지는 제거
                else: # 상어가 없다면
                    new_board[ni][nj] = (board[i][j][0], nd, board[i][j][2])

    board = new_board  # board가 이제 상어들의 새 위치를 가리키도록

# 이 코드가 핵심 코드
# 개씨바ㅓㄹ
def Next_Loc(i, j, speed, dir): # 상어의 다음 위치를 반환

    if dir == UP or dir == DOWN:  # i
        cycle = R * 2 - 2 # 사이클을 구해서 반복작업 최소화
        if dir == UP:
            speed += 2 * (R - 1) - i
        else:
            speed += i

        speed %= cycle
        if speed >= R:
            return (2 * R - 2 - speed, j, UP)
        return (speed, j, DOWN)

    else:  # j
        cycle = C * 2 - 2 # 사이클을 구해서 반복작업 최소화
        if dir == LEFT:
            speed += 2 * (C - 1) - j
        else:
            speed += j

        speed %= cycle
        if speed >= C:
            return (i, 2 * C - 2 - speed, LEFT)
        return (i, speed, RIGHT)


UP, DOWN, RIGHT, LEFT = 1, 2, 3, 4
R, C, M = map(int, sys.stdin.readline().split())
board = [[0 for _ in range(C)] for _ in range(R)]

for i in range(M):
    r, c, s, d, z = map(int, sys.stdin.readline().split())
    r, c = r - 1, c - 1
    board[r][c] = (s, d, z)
    # s : speed
    # d : 1(up), 2(down), 3(right), 4(left)
    # z : size


result = 0
for j in range(C):
    result += Catch_Shark(j)
    Shark_Move()

print(result)