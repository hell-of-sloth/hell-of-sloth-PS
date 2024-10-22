# 체감 난이도 4/10, 첨에 감 못 잡았지만 시간 제한을 보고 브루트포스라는 것을 짐작
# 그래서 쌩 브루트 했더니 시초 -> 좀 줄이는 브루트 포스(set 이용 중복 제거)
# 통과 -> 테케 출력 까먹지 말기

from collections import deque

T = int(input().strip())

def change_position(coins, change):
    max_coin = 0
    
    q = deque()
    duple = set()
    q.append((coins, change))
    
    while q:
        coin, cnt = q.popleft()
        
        if cnt == 4:
            pass
        
        if cnt == 0:
            max_coin = max(max_coin, int(''.join(coin)))
            continue
        
        for i in range(len(coin)-1):
            for j in range(i+1, len(coin)):
                temp = coin[:]
                temp[i], temp[j] = temp[j], temp[i]
                temp_set = (int(''.join(temp)), cnt-1)
                if temp_set not in duple:
                    duple.add(temp_set)
                    q.append((temp, cnt-1))
                    
    return max_coin

def solve(i):
    coins, change = input().split()
    change = int(change)
    result = change_position(list(coins), change)
    print('#{} {}'.format(i, result))
    
for i in range(1, T+1):
    solve(i)