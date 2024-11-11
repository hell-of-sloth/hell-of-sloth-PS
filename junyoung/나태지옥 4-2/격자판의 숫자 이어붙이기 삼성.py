# 체감 난이도 4/10, set랑 백트랙킹 이용
# 돌아갈땐 방문처리를 풀어줘야함


def solve():
    matrix = [list(input().split()) for _ in range(4)]
    
    result = set()
    visited = [[False]*4 for _ in range(4)]
    
    def backtrack(x, y, depth, path):
        
        if depth == 7:
            result.add(path)
            return
        
        for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
            nx, ny = x + dx, y + dy
            if 0 <= nx < 4 and 0 <= ny < 4:
                visited[nx][ny] = True
                backtrack(nx, ny, depth+1, path + matrix[nx][ny])
                visited[nx][ny] = False
                
    for i in range(4):
        for j in range(4):
            visited[i][j] = True
            backtrack(i, j, 1, matrix[i][j])
            visited[i][j] = False
            
    return len(result)

T = int(input())

for t in range(1, T+1):
    print('#{} {}'.format(t, solve()))
