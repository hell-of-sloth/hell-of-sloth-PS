# 체감 난이도 3/10, BFS인가 했는데? 가중치랑 차단거리 + 최소 가중치를 구하는 문제여서 다익스트라로 바로 품


from heapq import heappush, heappop

T = int(input())

def solve():
    N = int(input())
    M = [list(input()) for _ in range(N)]
    
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    dist = [[float('inf')] * N for _ in range(N)]
    hq = []
    heappush(hq, (0, 0, 0))
    dist[0][0] = 0
    
    while hq:
        cost, x, y = heappop(hq)

        if dist[x][y] != cost:
            continue
        
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            
            if 0 <= nx < N and 0 <= ny < N:
                ncost = cost + int(M[nx][ny])
                
                if dist[nx][ny] > ncost:
                    dist[nx][ny] = ncost
                    heappush(hq, (ncost, nx, ny))
                    
    print(dist[N - 1][N - 1])
    
for t in range(1, T+1):
    print("#{}".format(t), end=' ')
    solve()
