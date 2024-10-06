import sys
from collections import deque

T = int(sys.stdin.readline().rstrip()) # 테스트 케이스의 개수

def ACM(): # 위상 정렬, 다이나믹 프로그래밍 활용
    N, K = map(int, sys.stdin.readline().split())
    time = list(map(int, sys.stdin.readline().split()))
    time.insert(0, 0)
    new_time = time[:] # 저장 시간 리스트
    graph = [[] for _ in range(N+1)]
    
    indegree = [0] * (N+1) # 진입 차수
    for _ in range(K):
        X, Y = map(int, sys.stdin.readline().split())
        indegree[Y] += 1 # 진입 차수 개수 증가
        graph[X].append(Y)
    win_building = int(sys.stdin.readline().rstrip())
    
    queue = deque()
    for i in range(1, N+1):
        if indegree[i] == 0: # 진입 차수가 0인 노드를 큐에 삽입
            queue.append(i)
            
    while queue:
        now = queue.popleft()
        for i in graph[now]:
            indegree[i] -= 1 # 진입 차수 개수 감소
            new_time[i] = max(new_time[i], time[now] + time[i]) # 저장 시간 갱신
            if indegree[i] == 0: # 진입 차수가 0이 되면 큐에 삽입 및 저장시간을 기존 시간으로 변경
                time[i] = new_time[i]
                queue.append(i)
                
    sys.stdout.write(str(time[win_building]) + '\n')
    
for _ in range(T):
    ACM() # 응애