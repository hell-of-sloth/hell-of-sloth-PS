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

buildings = list(map(int, sys.stdin.readline().split())) # 튜플 쓰면 오히려 더 느림 왜지?
    
# solution (기울기 계산이랑 비교하는 부분을 같이), 근데 차이 없음
result = 0

for i in range(N):
    
    max_buildings = 0
    
    temp = buildings[i]
    
    for k in range(i - 1, -1, -1):
        l_incline = (buildings[k] - temp) / (i - k) # 계산 횟수 줄이니까 4ms 빨라짐
        if k == i - 1:
            max_buildings += 1
            left_max = l_incline
        else:
            if l_incline > left_max:
                left_max = l_incline
                max_buildings += 1
                
    for k in range(i + 1, N):
        r_incline = (buildings[k] - temp) / (k - i)
        if k == i + 1:
            max_buildings += 1
            right_max = r_incline
        else:
            if r_incline > right_max:
                right_max = r_incline
                max_buildings += 1
            
    if result < max_buildings:
        result = max_buildings

print(result)