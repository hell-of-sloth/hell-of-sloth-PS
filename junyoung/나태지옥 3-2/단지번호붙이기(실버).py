N = int(input())

MAP = [] # 지도
danji = [] # 단지

for i in range(N):
    MAP.append(list(map(int, input())))
    
def Find_house(): # 집 찾기
    global MAP, N
    
    for i in range(N):
        for j in range(N):
            if MAP[i][j] == 1:
                return j, i
    return -1, -1   
    

def DFS(x, y): # DFS
    global MAP, N, danji
    
    stack = [[x, y]]
    MAP[y][x] = 0
    move_position = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    count = 1
    
    while stack:
        node_x, node_y = stack.pop() 
        for move_x, move_y in move_position:
            next_x, next_y = node_x + move_x, node_y + move_y
            if 0 <= next_x < N and 0 <= next_y < N and MAP[next_y][next_x] == 1:
                stack.append([next_x, next_y])
                MAP[next_y][next_x] = 0     # 방문처리
                count += 1
                
    danji.append(count)
    danji.sort() # 오름차순 정렬
        
while True: # 집이 없을 때까지 반복
    x, y = Find_house()
    if x == -1 and y == -1:
        break
    else:
        DFS(x, y)
        
print(len(danji))
print(*danji, sep = '\n')