# 체감 난이도 2/10, 순간 구현인가 했으나, 단순히 패턴 찾아서 세는거라 간단히 풂

def solve():
    N = int(input())
    
    M = [list(map(int, input().split())) for _ in range(100)]
    
    diag_M = list(zip(*M))
    
    cnt = 0
    
    for i in range(100):
        flag = False
        for j in range(100):
            if diag_M[i][j] == 1:
                flag = True
            elif flag == True and diag_M[i][j] == 2:
                cnt += 1
                flag = False
                
    return cnt
            
for t in range(1, 11):
    print('#{} {}'.format(t, solve()))
    