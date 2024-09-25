'''
찬솔이는 
X일 동안 가장 많이 들어온 방문자 수와 그 기간들을 알고 싶다.

찬솔이를 대신해서 
X일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 구해주자.
'''

import sys

N, X = map(int, sys.stdin.readline().split())
visitors = list(map(int, sys.stdin.readline().split()))

max_visitors = 0
terms = 0
sum_visitors = 0

for i in range(N+1-X):
    if i == 0:
        sum_visitors = sum(visitors[i:i+X])
    else:
        sum_visitors = sum_visitors - visitors[i-1] + visitors[i+X-1]
    
    if sum_visitors > max_visitors:
        max_visitors = sum_visitors
        terms = 1
    elif sum_visitors == max_visitors:
        terms += 1
    
if max_visitors == 0:
    print("SAD")
else:
    print(max_visitors)
    print(terms)