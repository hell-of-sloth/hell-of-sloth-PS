import sys

N, L = map(int, sys.stdin.readline().split())

map_matrix = []

for _ in range(N):
    map_matrix.append(list(map(int, sys.stdin.readline().split())))
    
def is_possible_road(road): # 길이 가능한지 확인, True or False 반환
    global N, L
    
    check_flag = [False] * N
    
    for i in range(N-1):
        if road[i] == road[i+1]: # 높이가 같은 경우
            continue
        elif abs(road[i] - road[i+1]) == 1: # 높이 차이가 1인 경우
            if road[i] > road[i+1]: # 내리막길
                if i+L >= N:
                    return False
                for j in range(i+1, i+L+1):
                    if road[i+1] != road[j] or check_flag[j]:
                        return False
                    else:
                        check_flag[j] = True
            else: # 오르막길
                if i-L+1 < 0:
                    return False
                for j in range(i, i-L, -1):
                    if road[i] != road[j] or check_flag[j]:
                        return False
                    else:
                        check_flag[j] = True
        else: # 높이 차이가 1보다 큰 경우
            return False
        
    return True
    
def check_road(matrix): # 가능한 길이 몇 개 있는지 확인, 길 갯수 반환
    global N, L
    
    roads = []
    count = 0
    
    for i in range(N):
        roads.append(matrix[i])
        
    for i in range(N):
        roads.append([matrix[j][i] for j in range(N)])
        
    for road in roads:
        if is_possible_road(road):
            count += 1
            
    return count

print(check_road(map_matrix))