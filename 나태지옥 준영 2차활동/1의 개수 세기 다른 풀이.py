import sys

# 비트 쉬프트 이용

A, B = map(int, sys.stdin.readline().split())

dp = [0] * 60
dp[1] = 1

def bit_bungi(value, n):
    return value * 2 + (1 << (n-1))

def bit_cal(value):
    result = 0
    bin_len = len(bin(value))-2
    for i in range(bin_len, 0, -1):
        if value & (1 << (i-1)):
            result += dp[i-1]
            result += value - (1 << (i-1)) + 1
            value -= 1 << (i-1)
    return result
        
for i in range(2, 60):
    dp[i] = bit_bungi(dp[i-1], i)
    
print(bit_cal(B) - bit_cal(A-1))