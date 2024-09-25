import sys

N  = sys.stdin.readline().strip()

numList = [0 for _ in range(9)]

for i in range(len(N)):
    if N[i] == '9':
        numList[6] += 1
    else:
        numList[int(N[i])] += 1
        
numList[6] = (numList[6] + 1) // 2

print(max(numList))