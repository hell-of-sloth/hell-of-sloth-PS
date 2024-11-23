# 체감 난이도 1/10, 투 포인터
# 76ms in python3
# 딱 봤을 때 투 포인터 생각남


import sys

def solve():
    n = int(sys.stdin.readline().strip())
    L = list(map(int, sys.stdin.readline().split()))
    x = int(sys.stdin.readline().strip())
    
    L.sort()
    left = 0
    right = n - 1
    answer = 0
    
    while left < right:
        if L[left] + L[right] == x:
            answer += 1
            left += 1
            right -= 1
        elif L[left] + L[right] < x:
            left += 1
        else:
            right -= 1
            
    print(answer)
    
solve() 