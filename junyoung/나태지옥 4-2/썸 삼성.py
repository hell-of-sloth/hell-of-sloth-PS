# 체감 난이도 1/10, zip을 이용해서 열 행 바꾸기
# 행단위로 해서 접근속도 줄이기


def solve():
    L = [list(map(int, input().split())) for _ in range(100)]
    
    diag_L = list(zip(*L)) # 핵심 키워드 -> 열 행 바꾸기
    
    max_sum = 0
    sum1 = 0
    sum2 = 0
    
    for i in range(100):
        max_sum = max(max_sum, sum(L[i]), sum(diag_L[i]))
        sum1 += L[i][i]
        sum2 += L[i][99 - i]
        
    max_sum = max(max_sum, sum1, sum2)
    
    return max_sum

for _ in range(10):
    T = int(input())
    print("#{} {}".format(T, solve()))
    
    