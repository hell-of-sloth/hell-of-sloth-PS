
def solve():
    N = int(input())
    
    snail = [[0] * N for _ in range(N)]
    
    direction = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    
    current = [0, 0]
    current_direction = 0
    for i in range(1, N**2 + 1):
        snail[current[0]][current[1]] = i
        next_x, next_y = current[0] + direction[current_direction][0], current[1] + direction[current_direction][1]
        
        if 0 <= next_x < N and 0 <= next_y < N and snail[next_x][next_y] == 0:
            current = [next_x, next_y]
        else:
            current_direction = (current_direction + 1) % 4
            current = [current[0] + direction[current_direction][0], current[1] + direction[current_direction][1]]
        
    return snail
    
    
T = int(input())

for t in range(1, T+1):
    result = solve()
    print('#{}'.format(t))
    for i in range(len(result)):
        print(' '.join(map(str, result[i])))

