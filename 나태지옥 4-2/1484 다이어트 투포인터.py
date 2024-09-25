# 체감 난이도 4/10, 투 포인터란걸 알면 매우 쉬움

import sys

G = int(sys.stdin.readline().strip())

def calcul():
    present = 2 # 현재 몸무게, 1부터 시작하면 1**2 - 1**2 = 0이 되어버림
    old = 1  # 이전 몸무게
    
    flag = False # 가능한 몸무게가 있는지 확인하는 플래그
    
    while True:
        if present - old == 1 and present**2 - old**2 > G: # 몸무게 차이가 1인데 그 차이가 G보다 크면 앞으로도 계속 커질 수 밖에 없음 -> 종료
            if not flag: # 가능한 몸무게가 없다면
                print(-1)
                return
            return

        if present**2 - old**2 == G:
            flag = True
            print(present)
            present += 1 # 가능한 몸무게가 여러개일 수 있으므로 계속 탐색
            
        elif present**2 - old**2 < G: # 차이가 G보다 작으면 현재 몸무게를 늘려본다 -> 차이 늘리기
            present += 1
            
        else: # 차이가 G보다 크면 이전 몸무게를 늘려본다 -> 차이 줄이기
            old += 1
                    
calcul()