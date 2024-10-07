import sys
from collections import deque

N = int(sys.stdin.readline().rstrip()) # N*N

board = []
time = 0 # 총 시간

for _ in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))
    
def Baby_Shark(): # 아기상어 위치 찾기
    global N, board
    
    for i in range(N):
        for j in range(N):
            if board[i][j] == 9:
                return j, i # x, y 로 반환
            
def BFS(x, y, size, eat): # 아기상어 이동 BFS이용
    global N, board
    move_time = 0 # 이동 시간
    
    visited = [[False] * N for _ in range(N)] # 방문 표시
    direction = [[0, -1], [-1, 0], [1, 0], [0, 1]] # 상, 좌, 우, 하
    origin_x, origin_y = x, y # 원래 위치 저장
    
    eat_result = [] # 같은 위치 일때, 먹을 수 있는 물고기 저장
    
    queue = deque()
    new_queue = deque()
    queue.append([x, y])
    visited[y][x] = True
    while True:
        move_time += 1 # 이동 시간 1증가
        while queue:
            x, y = queue.popleft()
            
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                
                if nx < 0 or nx >= N:
                    continue
                
                if ny < 0 or ny >= N:
                    continue
                
                if visited[ny][nx] == False:
                    # 먹을 수 있는 물고기가 있을 때 eat_result에 저장
                    if board[ny][nx] > 0 and board[ny][nx] < size:
                        visited[ny][nx] = True
                        eat_result.append([nx, ny, size, eat, move_time])
                    # 같은 크기의 물고기가 있을 때 방문만
                    elif board[ny][nx] == size or board[ny][nx] == 0:
                        new_queue.append([nx, ny])
                        visited[ny][nx] = True
                    # 먹을 수 없는 물고기가 있을 때 건너뜀
                    else:
                        continue
                    
        # 먹을 수 있는 물고기가 있을 때
        if eat_result:
            eat += 1
            if eat == size: # 자신의 크기만큼 먹었을 때
                size += 1
                eat = 0
            eat_result.sort(key=lambda x: (x[4], x[1], x[0])) # 가장 가까운 물고기, 가장 위, 가장 왼쪽 순으로 정렬
            nnx, nny, nsize, neat, nmove_time = eat_result[0]
            board[nny][nnx] = 9 # 아기상어 위치 변경
            board[origin_y][origin_x] = 0 # 원래 아기상어 위치 0으로 변경
            return nnx, nny, size, eat, nmove_time # 반환
        
        # 먹을 수 있는 물고기가 없을 때, 다시 BFS 돌기
        if new_queue:
            queue = new_queue
            new_queue = deque()
        else: # 먹을 수 있는 물고기가 없고, 다음 이동할 곳도 없을 때
            break
        
    return -1, -1, size, eat, 0

def main(): # 메인함수
    global N, board, time
    
    x, y = Baby_Shark() # 아기상어 위치 찾기
    #기본 설정 크기 2, 먹은 물고기 0
    size = 2
    eat = 0
    while True:
        x, y, size, eat, move_time = BFS(x, y, size, eat) # BFS 돌기
        # for i in range(N):
        #     print(board[i])
        # print("size : ", size, "eat : ", eat, "time : ", time+move_time, "\n")
        if move_time == 0: # 더 이상 먹을 수 있는 물고기가 없을 때
            break
        time += move_time
        
    print(time)
    
main()