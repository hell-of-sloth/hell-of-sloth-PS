# 체감 난이도 6/10, 힙 사용은 알았지만 2개 이용하는 것은 몰랐음

import sys
import heapq

T = int(sys.stdin.readline().strip())

for _ in range(T):
    M = int(sys.stdin.readline().strip())
    
    elements = []
    
    for _ in range(M//10 + 1):
       elements.extend(list(map(int, sys.stdin.readline().split())))
       
    center_count = M // 2 + 1 # 중간값의 개수
    
    max_heap = [] # 중간값보다 작은 원소를 저장할 최대 힙
    min_heap = [] # 중간값보다 큰 원소를 저장할 최소 힙
    result = []
    
    for i in range(len(elements)):
        if i == 0:
            heapq.heappush(max_heap, -elements[i])
            result.append(elements[i])
            continue
        
        # 최대 힙의 크기는 최소 힙의 크기와 같거나, 하나 더 큼
        if len(max_heap) == len(min_heap):
            heapq.heappush(max_heap, -elements[i])
        else:
            heapq.heappush(min_heap, elements[i])
        
        # 최대 힙의 최대 원소는 최소 힙의 최소 원소보다 작거나 같아야 함
        # 알고리즘에 맞지 않다면 최대 힙, 최소 힙의 가장 위의 값을 꺼내서 바꿔치기
        if min_heap and -max_heap[0] > min_heap[0]:
            max_value = -heapq.heappop(max_heap)
            min_value = heapq.heappop(min_heap)
            
            # heapify 말고 push, pop을 이용해서 바꿔치면 좀 더 빠를 듯
            heapq.heappush(max_heap, -min_value)
            heapq.heappush(min_heap, max_value)
        
        # 인덱스가 짝수일 때가 홀수번째 임
        if i % 2 == 0:
            result.append(-max_heap[0])
    
    # 출력
    print(center_count)
    for i in range(center_count):
        print(result[i], end=' ')
        if i % 10 == 9 or i == center_count - 1: # 10개씩 출력 or 마지막 원소일 때
            print()

        