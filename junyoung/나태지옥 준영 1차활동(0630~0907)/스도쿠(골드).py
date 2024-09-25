import sys

sudoku = []

for _ in range(9):
    sudoku.append(list(map(int, sys.stdin.readline().rstrip())))

def check(x, y, num): # x, y에 num을 넣을 수 있는지 확인
    global sudoku
    for i in range(9):
        if sudoku[x][i] == num:
            return False
        if sudoku[i][y] == num:
            return False
    for i in range(3):
        for j in range(3):
            if sudoku[(x//3)*3+i][(y//3)*3+j] == num:
                return False
    return True

def Sudoku(): # 스도쿠 채우기
    global sudoku
    for i in range(9):
        for j in range(9):
            if sudoku[i][j] == 0: # 빈칸이면
                for k in range(1, 10):
                    if check(i, j, k):
                        sudoku[i][j] = k
                        Sudoku() # 재귀
                        sudoku[i][j] = 0 # 다시 빈칸으로
                return
    # 빈칸이 없으면 출력
    for i in range(9): # 출력
        for j in range(9):
            print(sudoku[i][j], end='')
        print()
    sys.exit() # 종료

print(Sudoku())