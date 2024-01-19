import sys

gears = [0]

for _ in range(4):
    gears.append(list(map(int, sys.stdin.readline().strip())))
    
def gearShift(gearlist, num, direction): # 1: 시계, -1: 반시계
    temp_gearlist = gearlist[:]
    gear_status = [0, 0, 0, 0, 0] # 기어의 회전 상태를 저장하는 리스트, 0: 회전하지 않음, 1: 시계방향, -1: 반시계방향
    
    if direction == 1:
        gear_status[num] = 1
    elif direction == -1:
        gear_status[num] = -1
        
    for i in range(num, 4):
        if gearlist[i][2] != gearlist[i+1][6]:
            gear_status[i+1] = -gear_status[i]
        else:
            break
    for i in range(num, 1, -1):
        if gearlist[i][6] != gearlist[i-1][2]:
            gear_status[i-1] = -gear_status[i]
        else:
            break
        
    for i in range(1, 5): # 기어 회전
        if gear_status[i] == 1:
            temp_gearlist[i] = [temp_gearlist[i][7]] + temp_gearlist[i][:7]
        elif gear_status[i] == -1:
            temp_gearlist[i] = temp_gearlist[i][1:] + [temp_gearlist[i][0]]
            
    return temp_gearlist

def calScore(gearlist): # 점수 계산
    score = 0
    for i in range(1, 5):
        if gearlist[i][0] == 1:
            score += 2**(i-1)
    return score

k = int(sys.stdin.readline().strip())

for _ in range(k):
    num, direction = map(int, sys.stdin.readline().split())
    gears = gearShift(gears, num, direction)
    
print(calScore(gears))