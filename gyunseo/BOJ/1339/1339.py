import sys
from itertools import permutations
input = sys.stdin.readline

def calc():
    summation = 0
    for s in stringList:
        len_s = len(s)
        num = 0
        for i in range(len_s - 1, -1, -1):
            num += mappingTable[ord(s[i]) - ord('A')] * (10 ** (len_s - 1 - i))
        summation += num
    return summation

def DFS(level, cur_digit):
    if level == numAlphabets - 1:
        global ans
        tmp_ans = calc()
        # print(tmp_ans)
        ans = max(ans, tmp_ans)
        return
    
    for i in range(10):
        if isUsed[i]: 
            continue
        isUsed[i] = True
        mappingTable[ord(alphabetList[level + 1]) - ord('A')] = i
        DFS(level + 1, i)
        isUsed[i] = False

if __name__ == "__main__":
    N = int(input().strip())
    stringList = [input().strip() for _ in range(N)]
    alphabetSet = set()
    ans = 0
    for string in stringList:
        for ch in string:
            alphabetSet.add(ch)
    
    # 0 -> alphabet0, 1 -> alphabet1
    alphabetList = list(sorted(alphabetSet))
    numAlphabets = len(alphabetList)
    isUsed = [False for _ in range(10)]
    # alphabet0 -> digit0, ...
    mappingTable = [-1 for _ in range(26)]
    permutationCandsGen = permutations(range(10), numAlphabets)
    for permutationCand in permutationCandsGen:
        for idx, digit in enumerate(permutationCand):
            mappingTable[ord(alphabetList[idx]) - ord('A')] = digit
        ans = max(ans, calc())
    # for i in range(10):
    #     isUsed[i] = True
    #     mappingTable[ord(alphabetList[0]) - ord('A')] = i
    #     DFS(0, i)
    #     isUsed[i] = False
    print(ans)