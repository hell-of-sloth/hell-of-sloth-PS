# 체감 난이도 4/10, BFS를 이용해서 사다리 거리를 구하는데 겹쳐있는게 있어, 조건을 명시하는게 까다로웠음

from collections import deque

def solve():
    T = int(input())
    
    ladders = [list(map(int, input().split())) for _ in range(100)]
    
    start_points = []
            
    min_dist = 1000000
    min_x = 0
    
    for i in range(100):
        if ladders[0][i] == 1:
    
            queue = deque()
            queue.append((0, i, 'd', 0))
            
            direction = [(0, 1), (0, -1)]
            
            while queue:
                x, y, d, dist = queue.popleft()
                
                if x == 99:
                    if min_dist >= dist:
                        min_dist = dist
                        min_x = i
                    break
                
                if d == 'd':
                    for dx, dy in direction:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < 100 and 0 <= ny < 100 and ladders[nx][ny] == 1:
                            if dy == 1:
                                queue.append((nx, ny, 'r', dist + 1))
                            else:
                                queue.append((nx, ny, 'l', dist + 1))
                            break
                    else:
                        queue.append((x + 1, y, 'd', dist + 1))
                    
                else:
                    if d == 'r':
                        ny = y + 1
                    else:
                        ny = y - 1
                        
                    if 0 <= ny < 100 and ladders[x][ny] == 1:
                        queue.append((x, ny, d, dist + 1))
                    else:
                        queue.append((x + 1, y, 'd', dist + 1))
                    
    return T, min_x

for _ in range(10):
    t, result = solve()
    print('#{} {}'.format(t, result))
    
