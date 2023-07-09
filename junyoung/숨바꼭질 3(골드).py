# 0-1 BFS : 가중치가 0인 노드는 앞에 넣기, 가중치가 1인 노드는 뒤에 넣기

from collections import deque

N, K = map(int, input().split())

def BFS():
    q = deque() # BFS를 위한 덱
    q.append(N)
    visited = [0] * 100001
    
    while q:
        x = q.popleft()
        if x == K:
            return visited[x]
        for nx in (x-1, x+1, x*2):
            if 0 <= nx < 100001 and visited[nx] == 0:
                if nx == x*2 and x != 0:    # x*2의 경우 가중치가 0이므로 앞에 넣기
                    visited[nx] = visited[x]
                    q.appendleft(nx)
                else:                       # x-1, x+1의 경우 가중치가 1이므로 뒤에 넣기
                    visited[nx] = visited[x] + 1
                    q.append(nx)
                    
print(BFS())  