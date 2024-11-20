# 체감 난이도 3/10, 누적합 문제 예제 적응해보기
# 걍 끝까지 계산해버리고 그후 구하기
# 시간은 좀 걸리는데 통과는 함 820ms
# 더 줄일 방법이 있나?
# 최대공약수가 1이면 보이는거임 -> gcd 이용 -> 더 뻐를려나?
# 112ms 나옴 8배 감소

import sys
from math import gcd

def solve():
    C = int(sys.stdin.readline().strip())
    
    num_sum = [0] * 1001
    
    for i in range(1, 1001):
        for j in range(0, i+1):
            gcd_result = gcd(i, j)
            if gcd_result == 1:
                if i == j:
                    num_sum[i] += 1
                else:
                    num_sum[i] += 2
        num_sum[i] += num_sum[i - 1]
        
    for _ in range(C):
        N = int(sys.stdin.readline().strip())
        print(num_sum[N])
        
solve()