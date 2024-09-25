N, M = map(int, input().split())

maze = []

for i in range(N):
    maze.append(list(map(int, input())))
    
# 무려 3중 반복문을 사용하는데, 이걸 어떻게 줄일 수 있을까?
def BFS():
    global maze
    min_count = float('inf')
    break_block = [[False] * M for _ in range(N)] # 벽을 부순적 있는지 여부를 나타낸다.
    
    while True: # 벽을 부술 수 있는 경우의 수를 모두 고려해야 하므로, while문을 사용한다.
        queue = [[0, 0]]
        breaking = False # 벽을 부순적 있는지 여부를 나타낸다.
        count = 0
        visited = [[False] * M for _ in range(N)]
        
        while True: # BFS를 사용한다. 한 번 돌때 마다 카운트 + 1
            temp_queue = []
            
            while queue: # queue에 있는 모든 좌표를 방문한다.
                x, y = queue.pop(0)
                visited[y][x] = True
                
                # 벽을 부술 수 있는 경우의 수를 모두 고려한다.
                
                if x > 0 and visited[y][x - 1] == False:
                    if maze[y][x - 1] == 0: # 벽을 부수지 않고 이동할 수 있는 경우
                        temp_queue.append([x - 1, y])
                    elif break_block[y][x - 1] == False and breaking == False: # 벽을 부수고 이동할 수 있는 경우
                        temp_queue.append([x - 1, y])
                        breaking = True
                        break_block[y][x - 1] = True
                if x < M - 1 and visited[y][x + 1] == False:
                    if maze[y][x + 1] == 0:
                        temp_queue.append([x + 1, y])
                    elif break_block[y][x + 1] == False and breaking == False:
                        temp_queue.append([x + 1, y])
                        breaking = True
                        break_block[y][x + 1] = True
                if y > 0 and visited[y - 1][x] == False:
                    if maze[y - 1][x] == 0:
                        temp_queue.append([x, y - 1])
                    elif break_block[y - 1][x] == False and breaking == False:
                        temp_queue.append([x, y - 1])
                        breaking = True
                        break_block[y - 1][x] = True
                if y < N - 1 and visited[y + 1][x] == False:
                    if maze[y + 1][x] == 0:
                        temp_queue.append([x, y + 1])
                    elif break_block[y + 1][x] == False and breaking == False:
                        temp_queue.append([x, y + 1])
                        breaking = True
                        break_block[y + 1][x] = True
                        
            queue = temp_queue
            count += 1
            if visited[N - 1][M - 1] == True: # 도착지에 도착하면, 최소 카운트를 갱신한다.
                if min_count > count:
                    min_count = count
                break
            elif len(queue) == 0: # queue가 비어있으면, 더 이상 갈 수 있는 곳이 없다는 뜻이므로, -1을 반환한다.
                break
            
        if breaking == False: # 벽을 부술 수 없는 경우
            return min_count
        
result = BFS()
if result == float('inf'):
    print(-1)
else:
    print(result)
    
# 개망작 난 아직도 멀었다....