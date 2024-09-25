'''
플레이어 간 매칭을 해주는 시스템은 다음과 같다.

플레이어가 입장을 신청하였을 때 매칭이 가능한 방이 없다면 
새로운 방을 생성하고 입장시킨다. 이떄 해당 방에는 
처음 입장한 플레이어의 레벨을 기준으로 -10부터 +10까지 입장 가능하다.
입장 가능한 방이 있다면 입장시킨 후 방의 정원이 모두 찰 때까지 대기시킨다.
이때 입장이 가능한 방이 여러 개라면 먼저 생성된 방에 입장한다.
방의 정원이 모두 차면 게임을 시작시킨다.
플레이어의 수 p, 플레이어의 닉네임 n, 플레이어의 레벨 l, 
방 한개의 정원 m이 주어졌을 때 위와 같은 방법으로 매칭해주고 
최종적으로 만들어진 방의 상태와 입장 플레이어들을 출력하는 프로그램을 작성하자.
'''

import sys

room_list = []

p, m = map(int, sys.stdin.readline().split())

for _ in range(p):
    l, n = map(str, sys.stdin.readline().split())
    
    l = int(l)
    
    if not room_list:
        if m == 1:
            room_list.append([True, [(l, n)]])
        else:
            room_list.append([False, [(l, n)]])
            
    else:
        flag = False
        
        for room in room_list:
            if room[0]:
                continue
            
            level = room[1][0][0]
            
            if level-10 <= l and l <= level+10:
                room[1].append((l, n))
                flag = True
                if len(room[1]) >= m:
                    room[0] = True
                break
            
        if not flag:
            if m == 1:
                room_list.append([True, [(l, n)]])
            else:
                room_list.append([False, [(l, n)]])
            
for room in room_list:
    room[1].sort(key=lambda x : x[1])
    
    if room[0] == True:
        sys.stdout.write("Started!"+'\n')
    else:
        sys.stdout.write("Waiting!"+'\n')
        
    for player_level, player_name in room[1]:
        sys.stdout.write(str(player_level) + ' ' + player_name + '\n')