# 체감 난이도 3/10, 누적합 문제 예제 적응해보기
# 걍 끝까지 계산해버리고 그후 구하기
# 시간은 좀 걸리는데 통과는 함 820ms
# 더 줄일 방법이 있나?

import sys

def solve():
    C = int(sys.stdin.readline().strip())
    
    num_sum = [0] * 1001
    matrix_set = set()
    for i in range(1, 1001):
        for j in range(1, 1001):
            if (i, j) in matrix_set:
                continue
            
            if i > j:
                num_sum[i] += 1
            else:
                num_sum[j] += 1
                
            temp = 1
            while i * temp <= 1000 and j * temp <= 1000:
                matrix_set.add((i * temp, j * temp))
                temp += 1
                
    for i in range(1, 1001):
        num_sum[i] += num_sum[i - 1]
    
    for _ in range(C):
        N = int(sys.stdin.readline().strip())
        print(num_sum[N]+2)
        
solve()