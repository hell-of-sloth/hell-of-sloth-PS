import sys
from collections import deque


input = sys.stdin.readline

def processPos(direction, pos):
    ret_i, ret_j = -1, -1
    # i = 0
    if direction == 1:
        ret_i, ret_j = (0, pos)
    # i = height
    elif direction == 2:
        ret_i, ret_j = (height, pos)
    # j = 0
    elif direction == 3:
        ret_i, ret_j = (pos, 0)
    # j = width
    elif direction == 4:
        ret_i, ret_j = (pos, width)

    return ret_i, ret_j

def OOB(i, j):
    if i < 0 or i > height:
        return True
    if j < 0 or j > width:
        return True
    return False

def BFS(target_pos):
    distance = [[0 for __ in range(width + 1)] for _ in range(height + 1)]
    q = deque()
    q.append(startPos)
    distance[startPos[0]][startPos[1]] = 1

    while q:
        cur_i, cur_j = q.popleft()
        
        for delta_i, delta_j in zip(deltaI, deltaJ):
            next_i, next_j = cur_i + delta_i, cur_j + delta_j
            if OOB(next_i, next_j):
                continue
            # 벽으로 막혀 있다면
            if board[next_i][next_j] == 1:
                continue
            # 이미 방문한 곳이라면
            if distance[next_i][next_j] > 0:
                continue
            
            distance[next_i][next_j] = distance[cur_i][cur_j] + 1
            q.append((next_i, next_j))
    
    return distance[target_pos[0]][target_pos[1]]

if __name__ == "__main__":

    width, height = map(int, input().strip().split())
    numTargets = int(input().strip())
    targetPosList = []
    deltaI = (-1, 1, 0, 0)
    deltaJ = (0, 0, -1, 1)
    for _ in range(numTargets):
        direction, pos = map(int, input().strip().split())
        
        targetPosList.append(processPos(direction, pos))
    
    direction, pos = map(int, input().strip().split())
    startPos = processPos(direction, pos)

    board = [[0 for __ in range(width + 1)] for _ in range(height + 1)]
    ans = 0
    # fill wall inner board
    for i in range(height + 1):
        for j in range(width + 1):
            if i == 0 or i == height:
                continue
            if j == 0 or j == width:
                continue
            board[i][j] = 1
    
    # print(startPos)
    # print(targetPosList)

    for target_pos in targetPosList:
        min_dist = BFS(target_pos)
        # print(min_dist)
        ans += min_dist - 1
    print(ans)
    