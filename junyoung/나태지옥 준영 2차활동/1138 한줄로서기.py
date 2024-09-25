'''
사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만을 기억한다. 
N명의 사람이 있고, 사람들의 키는 1부터 N까지 모두 다르다.

각 사람들이 기억하는 정보가 주어질 때, 줄을 어떻게 서야 하는지 출력하는 
프로그램을 작성하시오.
'''

import sys

N = int(sys.stdin.readline().strip())

key = list(map(int, sys.stdin.readline().split()))

result = [0] * N


for i in range(N):
    cnt = 0
    for j in range(N):
        if cnt == key[i] and result[j] == 0:
            result[j] = i+1
            break
        if result[j] == 0:
            cnt += 1
            
print(*result)