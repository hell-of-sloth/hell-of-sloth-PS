import sys

N, L = map(int, sys.stdin.readline().split())

map_matrix = []

for _ in range(N):
    map_matrix.append(list(map(int, sys.stdin.readline().split())))
    
def is_possible_road(road):
    global N, L
    
    check_flag = [False] * N
    
    for i in range(N-1):
        if road[i] == road[i+1]:
            continue
        elif abs(road[i] - road[i+1]) == 1:
            if road[i] > road[i+1]:
                if i+L >= N:
                    return False
                for j in range(i+1, i+L+1):
                    if road[i+1] != road[j] or check_flag[j]:
                        return False
                    else:
                        check_flag[j] = True
            else:
                if i-L+1 < 0:
                    return False
                for j in range(i, i-L, -1):
                    if road[i] != road[j] or check_flag[j]:
                        return False
                    else:
                        check_flag[j] = True
        else:
            return False
        
    return True
    
    

def check_road(matrix): # 길이 있는지 확인
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