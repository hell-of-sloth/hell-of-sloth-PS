# 체감 난이도 3/10, 걍 풀면 되는 줄알았더니 시간초과 남 -> 배열의 합을 저장한 이중 배열 생성


import sys

N, M = map(int, sys.stdin.readline().split())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
arr_Sum = [[0] * (M+1) for _ in range(N+1)]

for i in range(1, N+1):
    for j in range(1, M+1):
        arr_Sum[i][j] = arr_Sum[i-1][j] + arr_Sum[i][j-1] - arr_Sum[i-1][j-1] + arr[i-1][j-1]

K = int(sys.stdin.readline().strip())

for _ in range(K):
    i, j, x, y = map(int, sys.stdin.readline().split())
    
    answer = 0
    
    answer = arr_Sum[x][y] - arr_Sum[i-1][y] - arr_Sum[x][j-1] + arr_Sum[i-1][j-1]
    
    sys.stdout.write(str(answer) + '\n')