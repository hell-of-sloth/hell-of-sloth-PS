from collections import deque

def find_start(maze):
    for i in range(100):
        for j in range(100):
            if maze[i][j] == '2':
                return [i, j]
    return None

def solve():
    t = int(input())
    maze = [list(input()) for _ in range(100)]
    
    start = find_start(maze)
    
    queue = deque([start])
    directions = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    
    while queue:
        x, y = queue.popleft()
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            
            if 0 <= nx < 100 and 0 <= ny < 100:
                if maze[nx][ny] == '3':
                    return [t, 1]
                if maze[nx][ny] == '0':
                    maze[nx][ny] = '1'
                    queue.append([nx, ny])
                    
    return [t, 0]

for _ in range(10):
    t, result = solve()
    print('#{} {}'.format(t, result))