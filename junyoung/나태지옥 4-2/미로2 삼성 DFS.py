# 체감 난이도 3/10, 일반적인 그래프 탐색 문제, BFS, DFS 중 뭐를 쓸꺼냐도 중요
# 해당 문제는 DFS로 푸는게 조금 더 빠름

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
    
    stk = [start]
    directions = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    
    while stk:
        x, y = stk.pop()
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            
            if 0 <= nx < 100 and 0 <= ny < 100:
                if maze[nx][ny] == '3':
                    return [t, 1]
                if maze[nx][ny] == '0':
                    maze[nx][ny] = '1'
                    stk.append([nx, ny])
                    
    return [t, 0]

for _ in range(10):
    t, result = solve()
    print('#{} {}'.format(t, result))
