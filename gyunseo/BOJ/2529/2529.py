import sys
from itertools import permutations

input = sys.stdin.readline


# def DFS(level, seq_str):
#     global maxStr, minStr
#     if level == K:
#         seq_str_int = int(seq_str)
#         if seq_str_int > int(maxStr):
#             maxStr = seq_str
#         if seq_str_int < int(minStr):
#             minStr = seq_str
#         return
#     for i in range(10):
#         if isUsed[i]:
#             continue
#         if not eval(f"{seq_str[-1]} {operators[level]} {i}"):
#             continue
#         isUsed[i] = True
#         DFS(i, seq_str + str(i))
#         isUsed[i] = False


if __name__ == "__main__":
    K = int(input().strip())
    operators = [*input().strip().split()]
    # print(operators)
    nums = [*range(10)]
    isUsed = [False for _ in range(10)]
    maxStr = "0"
    minStr = "9999999999"
    # for i in range(10):
    #     isUsed[i] = True
    #     DFS(0, str(i))
    #     isUsed[i] = False
    # print(nums)
    candSeqList = permutations(nums, K + 1)
    # print(candSeqList)

    for candSeq in candSeqList:
        isValid = True
        for i, operator in enumerate(operators):
            toBeEvaluated = operator.join(map(str, [candSeq[i], candSeq[i + 1]]))
            # print(toBeEvaluated)
            if not eval(toBeEvaluated):
                isValid = False
                break

        if isValid:
            candSeqStr = "".join(map(str, candSeq))
            candSeqInt = int(candSeqStr)
            if candSeqInt > int(maxStr):
                maxStr = candSeqStr
            if candSeqInt < int(minStr):
                minStr = candSeqStr
    print(maxStr)
    print(minStr)
