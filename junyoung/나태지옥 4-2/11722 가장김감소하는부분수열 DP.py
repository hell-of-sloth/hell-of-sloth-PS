# 체감 난이도 5/10, Dp로 풀려다 내가 생각한 방법이 아니어서
# 스택 비스무리한 걸 로 비슷한 문제 푼 기억이 있어 풀려다 아니어서 다시고민했는데 DP로 푸는 거였다
# 나는 씹빡대가리


import sys

N = int(sys.stdin.readline().strip())
A = list(map(int, sys.stdin.readline().split()))

dp = [1] * N


for i in range(N):
    for j in range(0, i):
        if A[i] < A[j]: # 앞에 수중 나보다 큰 수의 dp값+1 을 나랑 비교해서 넣기
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))