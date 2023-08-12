import sys
import heapq

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    k = int(sys.stdin.readline().rstrip())
    min_heap = [] # 최소 힙
    max_heap = [] # 최대 힙
    visited = [False] * 1000001 # 삭제된 원소 체크
    for i in range(k):
        op, num = sys.stdin.readline().rstrip().split()
        if op == 'I':   # 삽입
            heapq.heappush(min_heap, (int(num), i))
            heapq.heappush(max_heap, (-int(num), i))
            visited[i] = True
        else:           # 삭제
            if num == '1':  # 최대 힙에서 삭제
                while max_heap and not visited[max_heap[0][1]]:
                    heapq.heappop(max_heap)
                if max_heap:
                    visited[max_heap[0][1]] = False
                    heapq.heappop(max_heap)
            else:           # 최소 힙에서 삭제
                while min_heap and not visited[min_heap[0][1]]:
                    heapq.heappop(min_heap)
                if min_heap:
                    visited[min_heap[0][1]] = False
                    heapq.heappop(min_heap)
    while min_heap and not visited[min_heap[0][1]]: # 최소 힙에서 삭제
        heapq.heappop(min_heap)
    while max_heap and not visited[max_heap[0][1]]: # 최대 힙에서 삭제
        heapq.heappop(max_heap)
    if min_heap and max_heap: # 최소 힙과 최대 힙이 모두 존재하는 경우
        print(-max_heap[0][0], min_heap[0][0])
    else: # 최소 힙과 최대 힙이 모두 존재하지 않는 경우
        print('EMPTY')
