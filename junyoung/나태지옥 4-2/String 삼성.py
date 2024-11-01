# 체감 난이도 1/10, 파이썬은 슬라이싱으로 간단히 풀림

def solve():
    t = int(input())
    find_s = input()
    len_s = len(find_s)
    s = input()
    
    cnt = 0
    
    for i in range(len(s)-len_s+1):
        if s[i:i+len_s] == find_s:
            cnt += 1
            
    print('#{} {}'.format(t, cnt))
    
for t in range(10):
    solve()