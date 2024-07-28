import sys

input = sys.stdin.readline
from collections import deque

def debugBombSetTime():
    for i in range(R):
        for j in range(C):
            print(bombSetTime[i][j], end = "")
        print()
def OOB(i, j):
    if i < 0 or i >= R: return True
    if j < 0 or j >= C: return True
    return False

def setBombs():
    for i in range(R):
        for j in range(C):
            if board[i][j] == ".":
                board[i][j] = "O"
                bombSetTime[i][j] = globalTimer

def bombsExplode():
    # print("-"*20)
    # print(f"현재 시각 {globalTimer}초 직후")
    # debugBombSetTime()
    # print("-"*20)
    for i in range(R):
        for j in range(C):
            if board[i][j] == "O" and bombSetTime[i][j] == globalTimer - 3:
                board[i][j] = "."
                bombSetTime[i][j] = -1

                for delta_i, delta_j in zip(deltaI, deltaJ):
                    next_i, next_j = i + delta_i, j + delta_j
                    if OOB(next_i, next_j): continue
                    if bombSetTime[next_i][next_j] == -1: continue
                    # 얘도 터져야 하는 폭탄이기 때문에
                    if bombSetTime[next_i][next_j] == globalTimer - 3: continue

                    board[next_i][next_j] = "."
                    bombSetTime[next_i][next_j] = -1

def debugBoard():
    for i in range(R):
        for j in range(C):
            print(board[i][j], end = "")
        print()

if __name__ == "__main__":
    deltaI = (-1, 1, 0, 0)
    deltaJ = (0, 0, -1, 1)
    R, C, N = map(int, input().strip().split())
    globalTimer = 0
    # 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
    board = [list(input().strip()) for _ in range(R)]
    bombSetTime = [[-1 for __ in range(C)] for _ in range(R)]
    for i in range(R):
        for j in range(C):
            if board[i][j] == "O":
                bombSetTime[i][j] = globalTimer
    # 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
    globalTimer += 1
    while globalTimer <= N - 1:
        # 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
        if globalTimer % 2 == 1:
            globalTimer += 1
            setBombs()
            # print(f"{globalTimer - 1}초~ {globalTimer}초 동안 폭탄 설치, {globalTimer}초 직전에 폭탄 설치했다고 기록")
            # debugBoard()
        # 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
        else:
            globalTimer += 1
            bombsExplode()
            # print(f"{globalTimer - 1}초에서 1초 기다리고 {globalTimer}초 직후에 폭발")
            # debugBoard()
    
    # print("-"* 10)
    debugBoard()