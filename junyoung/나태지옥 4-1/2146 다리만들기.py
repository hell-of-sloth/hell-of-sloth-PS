from sys import stdin
from collections import deque

N = int(stdin.readline().strip())

board = []
board_set = set()
coast = []


for i in range(N):
    l  =  list(map(int, stdin.readline().split()))
    board.append(l)
    for j in range(N):
        if l[j] == 1:
            board_set.add((i, j))

# 해당 노드가 해안가인지 검사(주변에 0이 있는지)      
def is_coast(node):
    global N
    
    x, y = node
    
    for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
        nx, ny = x + dx, y + dy
        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue
        if board[nx][ny] == 0:
            return True
    return False
    
# 대륙을 번호별로 나누는 함수, 이 함수가 끝나면 각 대륙은 1로 구성되는게 아닌 각 인식 숫자로 구성
def split_continent():
    global board, board_set, N, coast
    
    count = 2 # 처음 인식표
    
    while board_set:
        start = board_set.pop()
        
        if is_coast(start): # 시작점이 해안가라면
            coast.append(start) # 해안가 리스트에 추가
        
        q = deque([start])
        while q:
            node = q.popleft()
            x, y = node
            board[x][y] = count
            
            for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                nx, ny = x + dx, y + dy
                if nx < 0 or nx >= N or ny < 0 or ny >= N:
                    continue
                if (nx, ny) in board_set:
                    board_set.discard((nx, ny))
                    if is_coast((nx, ny)): # 해안가라면
                        coast.append((nx, ny)) # 해안가 리스트에 추가
                    q.append((nx, ny))
        count += 1

# 가장 짧은 다리를 찾는 함수
def find_shortest_bridge():
    global board, coast, N
    
    min_dist = 1000000
    
    for x, y in coast:
        visited_set = set()
        q = deque()
        q.append((x, y, 0)) # x, y, distance
        visited_set.add((x, y))
        continent_flag = board[x][y] # 대륙 번호
        
        while q:
            flag = False
            x, y, d = q.popleft()
            
            for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
                nx, ny = x + dx, y + dy
                if nx < 0 or nx >= N or ny < 0 or ny >= N:
                    continue
                if (nx, ny) in visited_set:
                    continue
                if board[nx][ny] == 0: # 바다라면
                    q.append((nx, ny, d + 1))
                    visited_set.add((nx, ny))
                elif board[nx][ny] != continent_flag: # 다른 대륙이라면
                    min_dist = min(min_dist, d)
                    flag = True
                    break
            if flag:
                break
            
    return min_dist
                
split_continent()
print(find_shortest_bridge())