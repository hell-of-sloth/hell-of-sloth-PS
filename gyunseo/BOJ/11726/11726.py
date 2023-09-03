import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

n = int(input().rstrip())

dp = [0] * 1001

dp[1] = 1
dp[2] = 2

for i in range(3, 1000 + 1):
    dp[i] = (dp[i - 1] + dp[i - 2]) % 10007

print(f"{dp[n]}\n")
