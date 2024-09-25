import sys

dp = [0] * 10001
dp[1] = 1
dp[2] = 2
dp[3] = 3

stack = 1

for i in range(4, 10001): # 규칙 찾기, (1이 가능한거) 1 + (2가 가능한거) 2의 배수 갯수 + (3이 가능한거) (i - 3)번째 개수
    dp[i] = dp[i-3] + (i // 2) + 1

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    n = int(sys.stdin.readline().rstrip())
    print(dp[n])