# N이 10000이라서 O(n^2)이면 안됨
# 체감 난이도 3/10, DP 생각하기가 까다로웠음, 여러 조합을 비교하는 경우 DP를 고려할 수 있다
# 10000에서 백트래킹 안되는거 감지하고 DP로 생각

import sys

N = int(sys.stdin.readline().strip())

P = [0] + list(map(int, sys.stdin.readline().split()))

dp = [0] * (N + 1)

for i in range(1, N + 1):
    for j in range(1, i + 1):
        dp[i] = max(dp[i], P[j] + dp[i - j])
        
print(dp[N])