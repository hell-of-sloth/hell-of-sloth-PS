import sys

input = sys.stdin.readline


def combination(cur_len, cur_idx):
    # base condition 1
    if cur_len == M:
        ans.add(tuple(seq))
        return

    for i in range(cur_idx + 1, len(nums)):
        if isUsed[i]:
            continue
        isUsed[i] = True
        seq.append(nums[i])
        combination(cur_len + 1, i)
        isUsed[i] = False
        seq.pop()


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    nums = [*sorted(map(int, input().rstrip().split()))]
    seq = []
    ans = set()
    isUsed = [False for _ in range(len(nums))]
    # NCM 결과 출력 (단, 단조 증가 수열)
    for i in range(len(nums)):
        isUsed[i] = True
        seq.append(nums[i])
        combination(1, i)
        seq.pop()
        isUsed[i] = False
    for s in sorted(ans):
        print(" ".join(map(str, s)))
