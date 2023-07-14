import sys

def getGrad(x1,y1,x2,y2):
    return (y2-y1)/(x2-x1)

n = int(sys.stdin.readline())
buildings = list(map(int,sys.stdin.readline().split()))

answer = 0
for i, building in enumerate(buildings):
    tempAns = 0
    if i == 0:
        leftGrad = 0
    else:
        leftGrad = getGrad(i-1,buildings[i-1],i,building)
        tempAns+=1
    if i == n-1:
        rightGrad = 0
    else:
        rightGrad = getGrad(i,building,i+1,buildings[i+1])
        tempAns+=1
    for left in range(i-2,-1,-1):
        grad = getGrad(left, buildings[left],i,building)
        if grad<leftGrad:
            leftGrad = grad
            tempAns +=1
    for right in range(i+2,n):
        grad = getGrad(i, building, right, buildings[right])
        if grad>rightGrad:
            rightGrad = grad
            tempAns+=1
    answer = max(answer,tempAns)
print(answer)