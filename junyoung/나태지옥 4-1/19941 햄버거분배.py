'''
식탁의 길이 N, 햄버거를 선택할 수 있는 거리 K, 
사람과 햄버거의 위치가 주어졌을 때, 
햄버거를 먹을 수 있는 사람의 최대 수를 구하는 프로그램을 작성하시오.
'''

import sys

N, K = map(int, sys.stdin.readline().split())

hamberger = sys.stdin.readline().strip()
eaten = [False] * N

count = 0

for i in range(N):
    if hamberger[i] == 'P':
        for j in range(max(0, i-K), min(N, i+K+1)):
            if hamberger[j] == 'H' and not eaten[j]:
                eaten[j] = True
                count += 1
                break
            
print(count)