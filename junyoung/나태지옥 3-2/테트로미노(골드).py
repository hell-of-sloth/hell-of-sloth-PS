import sys

N, M = map(int, sys.stdin.readline().split())

matrix = []
for _ in range(N):
    matrix.append(list(map(int, sys.stdin.readline().split())))
    
def Tetromino(): # 그냥 다 찾기
    global matrix
    
    max_sum = 0 # 최대값
    
    for i in range(N):
        for j in range(M):
            # 긴거
            if i+3 < N:
                temp = matrix[i][j] + matrix[i+1][j] + matrix[i+2][j] + matrix[i+3][j]
                if max_sum < temp:
                    max_sum = temp
            if j+3 < M:
                temp = matrix[i][j] + matrix[i][j+1] + matrix[i][j+2] + matrix[i][j+3]
                if max_sum < temp:
                    max_sum = temp
            # 네모
            if i+1 < N and j+1 < M:
                temp = matrix[i][j] + matrix[i][j+1] + matrix[i+1][j] + matrix[i+1][j+1]
                if max_sum < temp:
                    max_sum = temp                   
            # ㄴ
            if i+2 < N and j+1 < M:
                temp1 = matrix[i][j] + matrix[i+1][j] + matrix[i+2][j] + matrix[i+2][j+1]
                temp2 = matrix[i][j] + matrix[i][j+1] + matrix[i+1][j+1] + matrix[i+2][j+1]
                temp3 = matrix[i][j] + matrix[i][j+1] + matrix[i+1][j] + matrix[i+2][j]
                temp4 = matrix[i][j+1] + matrix[i+1][j+1] + matrix[i+2][j+1] + matrix[i+2][j]
                max_temp = max(temp1, temp2, temp3, temp4)
                if max_sum < max_temp:
                    max_sum = max_temp
            if i+1 < N and j+2 < M:
                temp1 = matrix[i][j] + matrix[i][j+1] + matrix[i][j+2] + matrix[i+1][j]
                temp2 = matrix[i][j] + matrix[i][j+1] + matrix[i][j+2] + matrix[i+1][j+2]
                temp3 = matrix[i][j] + matrix[i+1][j] + matrix[i+1][j+1] + matrix[i+1][j+2]
                temp4 = matrix[i+1][j] + matrix[i+1][j+1] + matrix[i+1][j+2] + matrix[i][j+2]
                max_temp = max(temp1, temp2, temp3, temp4)
                if max_sum < max_temp:
                    max_sum = max_temp
            # ㄹ
            if i+2 < N and j+1 < M:
                temp1 = matrix[i][j] + matrix[i+1][j] + matrix[i+1][j+1] + matrix[i+2][j+1]
                temp2 = matrix[i][j+1] + matrix[i+1][j+1] + matrix[i+1][j] + matrix[i+2][j]
                max_temp = max(temp1, temp2)
                if max_sum < max_temp:
                    max_sum = max_temp
            if i+1 < N and j+2 < M:
                temp1 = matrix[i][j] + matrix[i][j+1] + matrix[i+1][j+1] + matrix[i+1][j+2]
                temp2 = matrix[i][j+1] + matrix[i][j+2] + matrix[i+1][j] + matrix[i+1][j+1]
                max_temp = max(temp1, temp2)
                if max_sum < max_temp:
                    max_sum = max_temp
            # ㅗ
            if i+1 < N and j+2 < M:
                temp1 = matrix[i][j] + matrix[i][j+1] + matrix[i][j+2] + matrix[i+1][j+1]
                temp2 = matrix[i][j+1] + matrix[i+1][j] + matrix[i+1][j+1] + matrix[i+1][j+2]
                max_temp = max(temp1, temp2)
                if max_sum < max_temp:
                    max_sum = max_temp
            if i+2 < N and j+1 < M:
                temp1 = matrix[i][j] + matrix[i+1][j] + matrix[i+2][j] + matrix[i+1][j+1]
                temp2 = matrix[i][j+1] + matrix[i+1][j+1] + matrix[i+2][j+1] + matrix[i+1][j]
                max_temp = max(temp1, temp2)
                if max_sum < max_temp:
                    max_sum = max_temp
                    
    return max_sum

print(Tetromino())