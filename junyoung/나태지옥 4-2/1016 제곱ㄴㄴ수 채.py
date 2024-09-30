# 체감 난이도 7/10, 소수 구해서 나누는게 더걸림, 근데 테스트는 시간초과인데 왜 통과지

import sys

m, M = map(int, sys.stdin.readline().split())

answer = M - m + 1
divSquare = [False] * (M-m+1)

for i in range(2, int(M**0.5+1)):
    square = i**2
    for j in range((((m-1)//square)+1)*square, M+1, square):
        if not divSquare[j-m]:
            divSquare[j-m] = True
            answer -= 1
print(answer)
            
        