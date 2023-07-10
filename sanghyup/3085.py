import sys

def invertMatrix(matrix):
    matrix = list(map(list, zip(*matrix)))
    return matrix

n = int(sys.stdin.readline())
board = []

for _ in range(n):
    row = list(sys.stdin.readline().rstrip())
    board.append(row)

answer = 0
for i in range(n):
    for _ in range(n):
        combo = True
        changed = False
        longestCombo = 1
        if _-1 >=0:
                if i-1>=0 and board[i-1][_-1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                elif i+1<n and board[i+1][_-1]==board[i][_]:
                    longestCombo+=1
                    changed = True
        for j in range(_, n-1):
            if j+1< n:
                if board[i][j+1]==board[i][_]:
                    longestCombo+=1
                elif changed == False and i-1>=0 and board[i-1][j+1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                elif changed == False and i+1<n and board[i+1][j+1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                else:
                    break
        print(i,_,board[i][_] ,longestCombo)
        if longestCombo > answer:
            answer = longestCombo

board = invertMatrix(board)

for i in range(n):
    for _ in range(n):
        combo = True
        changed = False
        longestCombo = 1
        if _-1 >=0:
                if i-1>=0 and board[i-1][_-1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                elif i+1<n and board[i+1][_-1]==board[i][_]:
                    longestCombo+=1
                    changed = True
        for j in range(_, n-1):
            if j+1< n:
                if board[i][j+1]==board[i][_]:
                    longestCombo+=1
                elif changed == False and i-1>=0 and board[i-1][j+1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                elif changed == False and i+1<n and board[i+1][j+1]==board[i][_]:
                    longestCombo+=1
                    changed = True
                else:
                    break
        print(i,_,board[i][_] ,longestCombo)
        
        if longestCombo > answer:
            answer = longestCombo

print(answer)
