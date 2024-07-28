import sys
from itertools import permutations

input = sys.stdin.readline

def updateSCVHPList(scv_hp_list):
    for i in range(len(scv_hp_list)):
        scv_hp_list[i] -= damages[i]

def checkAnySCVAlive(scv_hp_list):
    for i in range(len(scv_hp_list)):
        if scv_hp_list[i] > 0:
            return True

    return False

if __name__ == "__main__":
    N = int(input().strip())
    scvHPList = [*map(int, input().strip().split())]
    stack = []
    damages = (9, 3, 1)
    ans = int(1e9)
    permutationSet = set(permutations(scvHPList, N))
    for perm in permutationSet:
        stack.append((*perm, 0))
    
    while stack:
        *curSCVHPList, attackedCount = stack.pop()
        
        updateSCVHPList(curSCVHPList)
        attackedCount += 1

        if attackedCount >= ans: continue

        if not checkAnySCVAlive(curSCVHPList):
            # print(f"now scv hp list: {curSCVHPList}, attackedCount: {attackedCount}")
            ans = min(ans, attackedCount)
            continue
        
        curSCVHPList = [*filter(lambda x: x > 0, curSCVHPList)]
        newPermutationSet = set(permutations(curSCVHPList, len(curSCVHPList)))
        for newPerm in newPermutationSet:
            stack.append((*newPerm, attackedCount))
    
    print(ans)