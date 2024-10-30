# 체감 난이도 2/10, 전형적인 백트래킹, 변수처리만 잘 해주면 될듯


T = int(input())

def solve():
    N = int(input())
    
    board = [[0] * N for _ in range(N)]
    result = 0
    
    def nqueen(row):
        nonlocal N, result, board
        
        for i in range(N):
            for j in range(row):
                if board[j][i]:
                    break
                if 0 <= i - row + j < N and board[j][i - row + j]:
                    break
                if 0 <= i + row - j < N and board[j][i + row - j]:
                    break
            else:
                if row == N - 1:
                    result += 1
                else:
                    board[row][i] = 1
                    nqueen(row + 1)
                    board[row][i] = 0
                    
    nqueen(0)
    return result

for t in range(1, T+1):
    print('#{} {}'.format(t, solve()))
    