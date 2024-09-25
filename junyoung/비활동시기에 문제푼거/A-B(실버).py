import sys
from collections import deque

a, b = map(int, sys.stdin.readline().split())

def exchange():
    global a, b
    
    Q = deque()
    Q.append((a, 0))
    
    while Q:
        item, count = Q.popleft()
        
        if item == b: # 찾았다면
            return count + 1
        elif item * 2 == b or item * 10 + 1 == b: # 계산 결과가 만족하면
            return count + 2
        else: # 아니라면
            if item * 2 < b:
                Q.append((item * 2, count + 1))
            if item * 10 + 1 < b:
                Q.append((item * 10 + 1, count + 1))
                
    return -1 # 못 찾았다면

print(exchange())
        
        
    