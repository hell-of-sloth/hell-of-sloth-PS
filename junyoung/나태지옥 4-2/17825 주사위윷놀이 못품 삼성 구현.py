import sys

dices = list(map(int, sys.stdin.readline().split()))

# 0 ~ 20
route = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24 ,26, 28, 30, 32, 34, 36, 38, 40]
# 21 ~ 28
route_1 = [10, 13, 16, 19, 25, 30, 35, 40]
# 29 ~ 35
route_2 = [20, 22, 24, 25, 30, 35, 40]
# 36 ~ 43
route_3 = [30, 28, 27, 26, 25, 30, 35, 40]

route.extend(route_1)
route.extend(route_2)
route.extend(route_3)

result = 0

def score_cal(cnt, ans, mal):
    # mals = [0, 0, 0, 0]
    global dices, result, route
        
    if cnt == 10:
        result = max(result, ans)
        return
    
    for i in range(4):
        mals = mal[:]
        
        # 이미 도착한 말은 넘어감
        if mals[i] == -1:
            continue
        
        # 도착지점에 갈것 같은 말은 도착처리
        if mals[i] < 21 and mals[i] + dices[cnt] >= 21:
            mals[i] = -1
            score_cal(cnt + 1, ans, mals)
            continue
        elif 21 <= mals[i] < 29 and mals[i] + dices[cnt] >= 29:
            mals[i] = -1
            score_cal(cnt + 1, ans, mals)
            continue
        elif 29 <= mals[i] < 36 and mals[i] + dices[cnt] >= 35:
            mals[i] = -1
            score_cal(cnt + 1, ans, mals)
            continue
        elif 36 <= mals[i] < 44 and mals[i] + dices[cnt] >= 44:
            mals[i] = -1
            score_cal(cnt + 1, ans, mals)
            continue
        
        # 이동 및 겹치는 칸 동일 처리
        mals[i] += dices[cnt]
        
        # 분기점
        if mals[i] == 5:
            mals[i] = 21
        elif mals[i] == 10:
            mals[i] = 29
        elif mals[i] == 15:
            mals[i] = 36
        
        # 가운데 겹침
        elif mals[i] == 32:
            mals[i] = 25
        elif mals[i] == 40:
            mals[i] = 25
        
        # 가운데 겹침 + 1
        elif mals[i] == 33:
            mals[i] = 26
        elif mals[i] == 41:
            mals[i] = 26
        
        # 가운데 겹침 + 2
        elif mals[i] == 34:
            mals[i] = 27
        elif mals[i] == 42:
            mals[i] = 27
        
        # 가운데 겹침 + 3
        elif mals[i] == 28:
            mals[i] = 20
        elif mals[i] == 35:
            mals[i] = 20
        elif mals[i] == 43:
            mals[i] = 20
            
        # 같은 위치 피하기
        if mals[i] in mal and mals[i]:
            continue
        
        score_cal(cnt + 1, ans + route[mals[i]], mals)
        
score_cal(0, 0, [0, 0, 0, 0])
print(result)
        