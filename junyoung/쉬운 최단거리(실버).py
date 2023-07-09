import sys # 입력, 출력 시간 줄이기 위해 sys 모듈 사용

n, m = map(int, input().split())

matrix = []                             # 입력받은 행렬
check = [[False] * m for _ in range(n)] # 방문 여부 확인

for i in range(n):
    matrix.append(list(map(int, sys.stdin.readline().split())))
    
def Find_Start(): # 시작점 찾기
    for i in range(n):
        for j in range(m):
            if matrix[i][j] == 2:
                return j, i
            
def BFS(): # BFS 이용
    global matrix, check
    
    new_matrix = [[0] * m for _ in range(n)]
    
    queue = []
    x, y = Find_Start()
    queue.append([x, y])
    check[y][x] = True
    move_position = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    
    while queue:
        x, y = queue.pop(0)
        
        for move_x, move_y in move_position: # 상하좌우 이동
            if 0 <= x + move_x < m and 0 <= y + move_y < n:
                if matrix[y + move_y][x + move_x] == 1 and check[y + move_y][x + move_x] == False:
                    new_matrix[y + move_y][x + move_x] = new_matrix[y][x] + 1
                    check[y + move_y][x + move_x] = True
                    queue.append([x + move_x, y + move_y])
                    
    for i in range(n): # 방문 못하는 곳은 -1로 바꾸기
        for j in range(m):
            if matrix[i][j] == 1 and check[i][j] == False:
                new_matrix[i][j] = -1
                    
    return new_matrix

result = BFS()

for i in range(n):
    for j in range(m):
        sys.stdout.write(str(result[i][j]) + ' ')
    sys.stdout.write('\n')