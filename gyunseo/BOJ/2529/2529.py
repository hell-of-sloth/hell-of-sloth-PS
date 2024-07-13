import sys
from itertools import permutations

input = sys.stdin.readline

def check(a, b, op):
    if op == "<":
        return a < b
    else:
        return a > b
if __name__ == "__main__":
    K = int(input().strip())
    operators = input().strip().split()
 
    maxSeqSum = 0
    minSeqSum = 9999999999
    maxSeq, minSeq = None, None
    seqCandsGen = permutations(range(10), K + 1)

    for candSeq in seqCandsGen:
        isValid = True
        for i, operator in enumerate(operators):
            if not check(candSeq[i], candSeq[i + 1], operator):
                isValid = False
                break

        if isValid:
            seqSum = 0
            factor = 1
            for idx in range(K, -1, -1):
                seqSum += candSeq[idx] * factor
                factor *= 10
            # print(f"seqSum: {seqSum}")
            if seqSum > maxSeqSum:
                maxSeq = candSeq
                maxSeqSum = seqSum
            if seqSum < minSeqSum:
                minSeq = candSeq
                minSeqSum = seqSum
            # print(f"minSeq: {minSeq}")

    # print(maxSeq, minSeq)
    print("".join(map(str, maxSeq)))
    print("".join(map(str, minSeq)))
