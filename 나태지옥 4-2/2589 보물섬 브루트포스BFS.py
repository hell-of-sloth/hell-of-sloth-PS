# 체감 난이도 4/10, 브르트포스 써도 된다는 믿음이 필요

import sys

X, Y = map(int, sys.stdin.readline().split())

tresure_map = []
land_list = []
for i in range(X):
    tresure_map.append(list(sys.stdin.readline().strip()))
    for j in range(Y):
        if tresure_map[i][j] == 'L':
            land_list.append((i, j))

def BFS():
    queue = []
    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    max_distance = 0
    
    for i, j in land_list: # 땅이 있는 곳은 모두 최대 길이 탐색
        visited = [[0] * Y for _ in range(X)]
        
        queue.append((i, j, 0))
        visited[i][j] = 1
        
        while queue:
            x, y, distance = queue.pop(0)
            max_distance = max(max_distance, distance)
            
            for dx, dy in direction:
                nx, ny = x + dx, y + dy
                
                if 0 <= nx < X and 0 <= ny < Y and visited[nx][ny] == 0 and tresure_map[nx][ny] == 'L':
                    queue.append((nx, ny, distance + 1))
                    visited[nx][ny] = 1
                            
    return max_distance

print(BFS())