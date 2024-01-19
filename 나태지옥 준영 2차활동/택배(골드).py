import sys

N, C = map(int, sys.stdin.readline().split())

M = int(sys.stdin.readline().rstrip())

boxes = []
truck = [0 for _ in range(N)]
total_weight = 0

for i in range(M):
    boxes.append(tuple(map(int, sys.stdin.readline().split())))
    
boxes.sort(key=lambda x: (x[1])) # 박스의 목적지 별로 정렬, 목적지가 빠른 순으로 쌓는 것이 가장 많이 실을수 있음

for box_start, box_end, box_weight in boxes:
    
    possible_weight = min(box_weight, C - max(truck[box_start:box_end]))
            
    for i in range(box_start, box_end):
        truck[i] += possible_weight
        
    total_weight += possible_weight  
 
print(total_weight)