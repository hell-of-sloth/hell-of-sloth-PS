# 체감 난이도 7/10, DP 구상이 어려움


import sys

n, k = map(int, sys.stdin.readline().split())

money = []

for i in range(n):
    money.append(int(sys.stdin.readline().strip()))

money.sort()

# dp로 풀기
DP = [0] * (k + 1)

DP[0] = 1

for c in money:
    for i in range(c, k + 1):
        DP[i] += DP[i - c]
            
print(DP[k])

# range(c, k + 1) 이거 하는 이유 -> 큰거로 작은거는 못 만드니까
# DP[i] += DP[i - c] -> DP[i]는 DP[i - c]의 경우의 수를 더해 주면 됨 ex) 2단위 코인에서 DP[5] = DP[5] + DP[5-2] 

# https://aia1235.tistory.com/33 참고
    
