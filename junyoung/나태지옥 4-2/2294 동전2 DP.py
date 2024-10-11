# 입력범위 10000이라서 O(n^2)이면 안됨
# 체감 난이도 3/10, DP 문제, 여러 조합을 비교하는 경우 DP를 고려할 수 있다
# 예상 시복 n * k = 100 * 10000 = 1000000


import sys

n, k = map(int, sys.stdin.readline().split())

coins = [int(sys.stdin.readline().strip()) for _ in range(n)]

dp = [float('inf') for _ in range(k + 1)]

for i in range(1, k + 1):
    for coin in coins:
        if i == coin:
            dp[i] = 1
        elif i > coin:
            dp[i] = min(dp[i], dp[i - coin] + 1)
            
if dp[k] == float('inf'):
    print(-1)
else:
    print(dp[k])