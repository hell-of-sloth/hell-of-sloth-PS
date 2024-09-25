import sys
from collections import deque

N, L, R = map(int, sys.stdin.readline().split())

country = [ list(map(int, sys.stdin.readline().split())) for _ in range(N) ]

def start_point(check):
    for i in range(N):
        for j in range(N):
            if not check[i][j]:
                return (i, j)
    return False

def dfs():
    global country, L, R, N
    
    flag = False # 연합이 일어났는지 확인하는 flag
    
    check = set((i, j) for i in range(N) for j in range(N)) # 시간 줄이기 위해 set 사용
    direction = [(-1, 0), (0, 1), (1, 0), (0, -1)]
    
    while True:
        if not check:
            break
        start = check.pop()
        
        
        visited = set() # O(1) time complexity
        q = deque()
        q.append(start)
        visited.add(start)
        S = 0
        while q:
            x, y = q.popleft()
            S += country[x][y]
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                if (0 <= nx < N and 0 <= ny < N) and ((nx, ny) not in visited) and (L <= abs(country[x][y] - country[nx][ny]) <= R):
                    if (nx, ny) in check:
                        flag = True
                        q.append((nx, ny))
                        visited.add((nx, ny))
                        check.remove((nx, ny))

        for x, y in visited:
            country[x][y] = S // len(visited)

    # for i in range(N):
    #     for j in range(N):
    #         print(country[i][j], end=' ')
    #     print()
    # print()
    
    return flag

count = 0

while True:
    if dfs(): # 연합이 일어났다면
        count += 1
    else:
        print(count)
        break