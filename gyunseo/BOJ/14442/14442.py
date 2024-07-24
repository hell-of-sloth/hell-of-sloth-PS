import sys
from collections import deque

input = sys.stdin.readline

def OOB(i, j):
    if i < 0 or i >= N: return True
    if j < 0 or j >= M: return True
    return False

def BFS():
    q = deque()
    distance[0][0][0] = 1
    q.append((0, 0, 0))

    while q:
        # 부서진 벽 개수, 현 위치
        k, cur_i, cur_j = q.popleft()
        
        for delta_i, delta_j in zip(deltaI, deltaJ):
            next_i, next_j = cur_i + delta_i, cur_j + delta_j

            # 격자 밖으로 나가는 건 무시
            if OOB(next_i, next_j): continue
            # 이미 방문했던 곳은 안됨
            if distance[k][next_i][next_j] > 0: continue

            # 현 스테이지에서 계속 목적지를 향해 가거나, 벽을 부수고 다음 스테이지로 가서 목적지를 향해 가야 한다

            # 목적지로 향해 가지 못하는 경우를 제외한다

            # 조사할 좌표가 벽이고, 부서진 벽의 개수가 현 테케에서 허용하는 부술 수 있는 벽 개수와 같으면 더 이상 윗층 스테이지로 못간다
            if board[next_i][next_j] == 1 and k == K: continue

            # 윗층 스테이지에서 이미 한 바탕 휩쓸고 지나간 위치면 큐에 넣을 필요가 없다
            if board[next_i][next_j] == 1 and distance[k + 1][next_i][next_j] > 0: continue
            # 이제는 목적지로 향할 수 있다.
            # 1. 벽을 부수고 다음 스테이지로 가서, 목적지로 향한다
            if board[next_i][next_j] == 1:
                distance[k + 1][next_i][next_j] = distance[k][cur_i][cur_j] + 1
                q.append((k + 1, next_i, next_j))
            # 2. 현 스테이지에서 벽을 안 부수고 그냥 목적지로 향할 수 있다
            else:
                distance[k][next_i][next_j] = distance[k][cur_i][cur_j] + 1
                q.append((k, next_i, next_j))
                

            
        
if __name__ == "__main__":
    deltaI = (-1, 1, 0, 0)
    deltaJ = (0, 0, -1, 1)

    N, M, K = map(int, input().strip().split())
    board = [[*map(int, list(input().strip()))] for _ in range(N)]
    distance = [[[0 for ___ in range(M)] for __ in range(N)] for _ in range(K + 1)]
    BFS()
    ans = int(1e9)

    for k in range(K + 1):
        if distance[k][N - 1][M - 1] > 0:
            ans = min(ans, distance[k][N - 1][M - 1])
    print(-1 if ans == int(1e9) else ans)