import sys

n = int(sys.stdin.readline().strip())

dp = [0] + [4] * n
squares = [i ** 2 for i in range(1, int(n**0.5)+1)]

for i in range(1, n+1):
    for square in squares:
        if square > i:
            break
        dp[i] = min(dp[i], dp[i - square] + 1)
            
print(dp[n])