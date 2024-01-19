import sys

N, M = map(int, sys.stdin.readline().split())
robot_r, robot_c, robot_d = map(int, sys.stdin.readline().split())

# 0: 북, 1: 동, 2: 남, 3: 서

room_map = []
clean = 0
for _ in range(N):
    room_map.append(list(map(int, sys.stdin.readline().split())))


def clean_space(room):
    global robot_r, robot_c, robot_d, clean
    
    new_room = room[:]
    if new_room[robot_r][robot_c] == 0:
        new_room[robot_r][robot_c] = 2
        clean += 1
    
    return new_room

def check_space(room):
    global robot_r, robot_c, robot_d
    
    is_space = False
    direction = [(-1, 0), (0, 1), (1, 0), (0, -1)]
    for direc in direction:
        new_r = robot_r + direc[0]
        new_c = robot_c + direc[1]
        
        if room[new_r][new_c] == 0:
            is_space = True
            break
        
    return is_space
    
def robot_activation(room):
    global robot_r, robot_c, robot_d
    
    if check_space(room):
        if robot_d == 0:
            robot_d = 3
        else:
            robot_d -= 1
            
        if robot_d == 0:
            if room[robot_r - 1][robot_c] == 0:
                robot_r -= 1
        elif robot_d == 1:
            if room[robot_r][robot_c + 1] == 0:
                robot_c += 1
        elif robot_d == 2:
            if room[robot_r + 1][robot_c] == 0:
                robot_r += 1
        elif robot_d == 3:
            if room[robot_r][robot_c - 1] == 0:
                robot_c -= 1
            
        return True
    else:
        if robot_d == 0:
            robot_r += 1
        elif robot_d == 1:
            robot_c -= 1
        elif robot_d == 2:
            robot_r -= 1
        elif robot_d == 3:
            robot_c += 1
            
        if room[robot_r][robot_c] == 1:
            return False
        else:
            return True
        
def print_map(room):
    for i in range(N):
        for j in range(M):
            print(room[i][j], end=' ')
        print()
        
def robot_cleaning(room):
    global robot_r, robot_c, robot_d, clean
    
    while True:
        room = clean_space(room)
        if not robot_activation(room):
            break
        # print_map(room)
        # print("robot_r: ", robot_r, "robot_c: ", robot_c, "robot_d: ", robot_d)
        # print("clean: ", clean)
    
    print(clean)
    
robot_cleaning(room_map)