import sys

def invertMatrix(matrix):
    matrix = list(map(list, zip(*matrix)))
    return matrix

def getLongestCombo(i,_):
    global board
    changed = False
    longestCombo = 1
    for j in range(_-1, -1, -1):
        if board[i][j]==board[i][_]:
            longestCombo+=1
        else:
            break
    for j in range(_+1, n):
        if board[i][j]==board[i][_]:
            longestCombo+=1
        elif changed == False and i-1>=0 and board[i-1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        elif changed == False and i+1<n and board[i+1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        else:
            if j+1<n and changed == False and board[i][j+1]==board[i][_]:
                longestCombo+=1
            break
    result = longestCombo
    longestCombo = 1
    for j in range(_-1, -1, -1):
        if board[i][j]==board[i][_]:
            longestCombo+=1
        elif changed == False and i-1>=0 and board[i-1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        elif changed == False and i+1<n and board[i+1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        else:
            if j-1>=0 and changed == False and board[i][j-1]==board[i][_]:
                longestCombo+=1
            break
    for j in range(_+1, n):
        if board[i][j]==board[i][_]:
            longestCombo+=1
        elif changed == False and i-1>=0 and board[i-1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        elif changed == False and i+1<n and board[i+1][j]==board[i][_]:
            longestCombo+=1
            changed = True
        else:
            if j+1<n and changed == False and board[i][j+1]==board[i][_]:
                longestCombo+=1
            break
    if longestCombo > result:
        result = longestCombo
    return result

n = int(sys.stdin.readline())
board = []

for _ in range(n):
    row = list(sys.stdin.readline().rstrip())
    board.append(row)

answer = 0

for i in range(n):
    for _ in range(n):
        newanswer = getLongestCombo(i,_)
        if newanswer >answer:
            answer = newanswer
board = invertMatrix(board)

for i in range(n):
    for _ in range(n):
        newanswer = getLongestCombo(i,_)
        if newanswer >answer:
            answer = newanswer

print(answer)