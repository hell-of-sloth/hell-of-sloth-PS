import sys

input = sys.stdin.readline


def combination(cur_len):
    # base condition 1
    if cur_len == M:
        ansSet.add(tuple(seq))
        return

    for i in range(lenNums):
        if isUsed[i]:
            continue
        isUsed[i] = True
        seq.append(nums[i])
        combination(cur_len + 1)
        isUsed[i] = False 
        seq.pop()



if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    nums = [*sorted(map(int, input().rstrip().split()))]
    lenNums = len(nums)
    isUsed = [False for _ in range(lenNums)]
    seq = []
    ansSet = set()
    for i in range(lenNums):
        isUsed[i] = True
        seq.append(nums[i])
        combination(1)
        isUsed[i] = False
        seq.pop()
    for ans in sorted(ansSet):
        print(' '.join(map(str, ans)))
