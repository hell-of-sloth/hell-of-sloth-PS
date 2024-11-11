# 체감 난이도 4/10, Ladder2의 변형 문제, 코드만 살짝 변경
# 곂치는 사다리가 없긴하다
# 아래서 위로 올라가는 것으로 변경 + dist 삭제 + return 값은 y값으로 변경

from collections import deque

def solve():
    T = int(input())
    
    ladders = [list(map(int, input().split())) for _ in range(100)]
    
    start_points = []
            
    result = 0
    
    for i in range(100):
        if ladders[99][i] == 2:
    
            queue = deque()
            queue.append((99, i, 'u'))
            
            direction = [(0, 1), (0, -1)]
            
            while queue:
                x, y, d = queue.popleft()
                
                if x == 0:
                    result = y
                    break
                
                if d == 'u':
                    for dx, dy in direction:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < 100 and 0 <= ny < 100 and ladders[nx][ny] == 1:
                            if dy == 1:
                                queue.append((nx, ny, 'r'))
                            else:
                                queue.append((nx, ny, 'l'))
                            break
                    else:
                        queue.append((x - 1, y, 'u'))
                    
                else:
                    if d == 'r':
                        ny = y + 1
                    else:
                        ny = y - 1
                        
                    if 0 <= ny < 100 and ladders[x][ny] == 1:
                        queue.append((x, ny, d))
                    else:
                        queue.append((x - 1, y, 'u'))
                    
    return T, result

for _ in range(10):
    t, result = solve()
    print('#{} {}'.format(t, result))
    
