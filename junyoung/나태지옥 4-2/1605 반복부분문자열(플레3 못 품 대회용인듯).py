# 플레3 문제
# LCP, Suffix Array 이용하라는데 다 처음본다, 역시 플레인가?
# 구글링해서 파이썬 코드를 찾아보려는데 없다 파이썬으로 안 푸나보다
# 더 찾아보니 라빈-카프 해싱기번을 쓴다는데.... 이런거는 코테 안나올듯 대회용인가보다.

import sys

L_len = int(sys.stdin.readline().strip())
L = list(sys.stdin.readline().strip())

def hash_count():
    global L_len, L
    
    l = 1
    r = L_len - 1
    
    mid = (l + r) // 2
    
    while r > l:
        
        h = set()
        
        flag = False
        
        for i in range(L_len - mid + 1):
            if "".join(L[i:i+mid]) in h:
                flag = True
                break
            h.add("".join(L[i:i+mid]))
            
        if flag:
            l = mid + 1
        else:
            r = mid - 1
        mid = (l + r) // 2 
        
    if l == 1:
        return 0
    else:
        return l
        
print(hash_count())