'''
세준시에는 고층 빌딩이 많다.
세준시의 서민 김지민은 가장 많은 고층 빌딩이 보이는 고층 빌딩을 찾으려고 한다. 
빌딩은 총 N개가 있는데, 빌딩은 선분으로 나타낸다. i번째 빌딩 (1부터 시작)은 
(i,0)부터 (i,높이)의 선분으로 나타낼 수 있다. 고층 빌딩 A에서 다른 고층 빌딩 
B가 볼 수 있는 빌딩이 되려면, 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층 
빌딩을 지나거나 접하지 않아야 한다. 가장 많은 고층 빌딩이 보이는 빌딩을 구하고, 
거기서 보이는 빌딩의 수를 출력하는 프로그램을 작성하시오.
'''

import sys

N = int(sys.stdin.readline().strip())

buildings = list(map(int, sys.stdin.readline().split()))

incline = []

# 기울기를 구한다.
for i in range(N):
    temp = [0] * N
    for j in range(N):
        if i == j:
            continue
        
        if i < j:
            temp[j] = (buildings[j] - buildings[i]) / (j - i), 2
        else:
            temp[j] = (buildings[j] - buildings[i]) / (i - j), 2
        
    incline.append(temp)
    
    
# solution
# 기울기를 구하고, 기울기가 가장 큰 빌딩을 찾으면서 횟수 세기
# 다음 빌딩의 기울기가 더 크면 횟수 증가 및 기울기 갱신
# 기울기가 작으면 넘어가기

result = 0

for i in range(N):
    
    max_buildings = 0
    
    left_incline = incline[i][:i]
    right_incline = incline[i][i+1:]
    
    
    if left_incline: 
        left_max = left_incline[-1]
        max_buildings += 1
        for k in range(len(left_incline)-1, -1, -1):
            if left_incline[k] > left_max:
                left_max = left_incline[k]
                max_buildings += 1
            
    if right_incline:
        right_max = right_incline[0]
        max_buildings += 1
        for k in range(len(right_incline)):
            if right_incline[k] > right_max:
                right_max = right_incline[k]
                max_buildings += 1
    
    if result < max_buildings:
        result = max_buildings

print(result)