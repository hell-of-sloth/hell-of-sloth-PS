import sys
import heapq

N = int(sys.stdin.readline().rstrip())

class_room = []

for _ in range(N):
    class_room.append(tuple(map(int, sys.stdin.readline().split())))
    
def greedy_algorithm():
    global class_room
    
    class_room.sort()
    
    heap = [] # 끝나는 시간을 저장하는 힙
    heapq.heappush(heap, class_room[0][1])
    
    for i in range(1, N):
        if heap[0] <= class_room[i][0]: # 끝나는 시간이 가장 빠른 강의실에 배정
            heapq.heappop(heap)
            heapq.heappush(heap, class_room[i][1])
        else:                           # 새로운 강의실 배정
            heapq.heappush(heap, class_room[i][1])
            
    print(len(heap)) # 힙의 크기가 강의실의 개수
    
greedy_algorithm()