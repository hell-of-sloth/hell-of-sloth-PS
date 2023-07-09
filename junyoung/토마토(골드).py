from collections import deque # deque 이용

M, N = map(int, input().split()) # M: 가로, N: 세로

box = []                # 토마토 상자
tomato = deque()        # 익은 토마토 위치
count = 0               # 토마토가 전부 익는데 걸리는 시간

for i in range(N):
    box.append(list(map(int, input().split())))
   
# 익은 토마토 위치 찾기
def Search_Tomato(): 
    global tomato
    
    for i in range(N):
        for j in range(M):
            if box[i][j] == 1:
                tomato.append([j, i])

# 익지 않은 토마토가 있는지 확인          
def Search_Raw_Tomato():
    for i in range(N):
        for j in range(M):
            if box[i][j] == 0:
                return True
    return False

# 토마토 퍼트리기, 넓이 우선 탐색 이용
def Tomato_Speard():
    global tomato, count
    
    flag = False
    
    new_tomato = deque()
    
    while tomato:
        temp_tomato = tomato.popleft()
        x = temp_tomato[0]
        y = temp_tomato[1]
        
        if x > 0 and box[y][x-1] == 0:
            box[y][x-1] = 1
            flag = True
            new_tomato.append([x-1, y])
        if x < M-1 and box[y][x+1] == 0:
            box[y][x+1] = 1
            flag = True
            new_tomato.append([x+1, y])
        if y > 0 and box[y-1][x] == 0:
            box[y-1][x] = 1
            flag = True
            new_tomato.append([x, y-1])
        if y < N-1 and box[y+1][x] == 0:
            box[y+1][x] = 1
            flag = True
            new_tomato.append([x, y+1])
            
    if flag:            # 토마토가 퍼졌다면
        count += 1
    tomato = new_tomato # 새로운 토마토 위치 저장

# 메인
Search_Tomato()
while tomato:
    Tomato_Speard()
if Search_Raw_Tomato(): # 익지 않은 토마토가 있다면
    print(-1)
else:                   # 익지 않은 토마토가 없다면
    print(count)