import sys
from itertools import product # 중복순열

N = int(sys.stdin.readline().rstrip())
M = int(sys.stdin.readline().rstrip())

broken_button = list(map(int, sys.stdin.readline().split()))
num = []
for i in range(10):
    if i not in broken_button:
        num.append(i)

def RemoteController(): # 0 ~ 9 버튼으로 이동할 수 있는 경우의 수를 모두 구한 후, 최솟값을 구한다.
    global N, M, broken_button, num
    
    if N == 100:
        return 0
    elif M == 10:
        return abs(N - 100)
    else:
        min_cnt = abs(N - 100)
        for i in range(1, 7):
            for j in product(num, repeat=i):
                num_str = ''.join(map(str, j))
                min_cnt = min(min_cnt, abs(N - int(num_str)) + len(num_str))
        return min_cnt
    
print(RemoteController())