import sys
from itertools import combinations
input = sys.stdin.readline

def extractElements(bitmask):
    ret = []
    for i in range(N):
        if bitmask % 2:
            ret.append(i)
        bitmask //= 2
    
    return ret

def getNumElements(bitmask):
    ret = 0
    while bitmask:
        if bitmask % 2:
            ret += 1
        bitmask //= 2
    
    return ret


def calcDiff(start):
    """
    O(N)
    """
    tmp = 0
    for e in extractElements(start):
        tmp += sumRowList[e] + sumColList[e]
    return abs(sumMatrix - tmp)

                
if __name__ == "__main__":
    N = int(input().strip())
    S = [[*map(int, input().strip().split())] for _ in range(N)]
    numSet = 2 ** N - 1
    sumRowList = [sum(S[i]) for i in range(N)]
    sumColList = [sum(col) for col in zip(*S)]
    sumMatrix = sum(sumRowList)
    ans = int(1e9)
    for i in range(1, 2 ** N):
        numElements = getNumElements(i)
        if numElements > (N // 2):
            continue
        ans = min(ans, calcDiff(i))
    
    print(ans)
