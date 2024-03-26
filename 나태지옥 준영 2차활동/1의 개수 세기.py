'''
https://www.acmicpc.net/problem/9527 문제
'''

import sys

# 이진수 변환 리스트 이용

A, B = map(int, sys.stdin.readline().split())

dp = [0] * 60
dp[1] = 1

def bit_bungi(value, n):
    return value * 2 + 2**(n-1)

def bit_cal(value):
    result = 0
    bin_num = bin(value)[2:]
    while bin_num:
        if bin_num[0] == '1':
            result += dp[len(bin_num)-1]
            result += int(bin_num, 2) - 2**(len(bin_num)-1) + 1
        bin_num = bin_num[1:]
    return result
            

for i in range(2, 60):
    dp[i] = bit_bungi(dp[i-1], i)
    
print(bit_cal(B) - bit_cal(A-1))