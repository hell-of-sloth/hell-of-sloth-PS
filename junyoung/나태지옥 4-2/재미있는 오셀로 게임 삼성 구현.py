# 체감 난이도 4/10, 8방향 다 고려, 사이에 있는 돌들을 바꿔주는 것이 핵심 -> 생각보다 어려움
# 판이 안 넓어서 걍 갯수만 세도 통과 가능

T = int(input())

def solve():
    N, M = map(int, input().split())
    
    board = [[0] * N for _ in range(N)]
    board[N//2][N//2] = 2
    board[N//2-1][N//2-1] = 2
    board[N//2][N//2-1] = 1
    board[N//2-1][N//2] = 1
    
    black_cnt = 0
    white_cnt = 0
    
    def white_black(x, y, color):
        nonlocal N, board
        
        direction = [(0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1)]
        
        board[x][y] = color
            
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            while 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 0:
                    break
                if board[nx][ny] == color:
                    while not (nx == x and ny == y):
                        nx -= dx
                        ny -= dy
                        board[nx][ny] = color 
                    break
                nx += dx
                ny += dy

    
    for c in range(M):
        x, y, color = map(int, input().split())
        x -= 1
        y -= 1
        white_black(x, y, color)
        
    for i in range(N):
        for j in range(N):
            if board[i][j] == 2:
                white_cnt += 1
            elif board[i][j] == 1:
                black_cnt += 1
        
    return black_cnt, white_cnt

for t in range(1, T+1):
    b, w = solve()
    print("#{} {} {}".format(t, b, w))
        