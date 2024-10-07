R, C = map(int, input().split())
map = []
for _ in range(R):
    map.append(list(input()))
result = 0
alphas = set()
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def DFS(x, y, count):
    global result
    result = max(result, count)
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < R and 0 <= ny < C and not map[nx][ny] in alphas:
            alphas.add(map[nx][ny])
            DFS(nx, ny, count+1)
            alphas.remove(map[nx][ny])
alphas.add(map[0][0])
DFS(0, 0, 1)
print(result)