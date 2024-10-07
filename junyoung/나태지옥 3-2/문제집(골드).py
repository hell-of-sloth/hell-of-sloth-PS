import sys
import heapq # 우선순위 큐

N, M = map(int, sys.stdin.readline().split())

problems = [i for i in range(N+1)] # 문제집
order = [[] for _ in range(N+1)] # 문제 푸는 순서
reverse_order = [[] for _ in range(N+1)] # 역순서

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    order[b].append(a) # b문제를 풀기 위해 a문제를 풀어야 함
    reverse_order[a].append(b)
    
def ordering(): # 위상정렬
    heap = []
    for i in range(1, N+1):
        if not order[i]: # 선행문제가 없는 문제들을 힙에 넣음
            heapq.heappush(heap, i)
    
    while heap:
        now = heapq.heappop(heap) # 선행문제가 없는 문제들 중 가장 작은 번호의 문제를 뽑음
        sys.stdout.write(str(now) + ' ')
        for i in reverse_order[now]: # now문제를 풀면 풀 수 있는 문제들의 선행문제를 제거
            order[i].remove(now)
            if not order[i]:
                heapq.heappush(heap, i) # 선행문제가 없는 문제들을 힙에 넣음
ordering()