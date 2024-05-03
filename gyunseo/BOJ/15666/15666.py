import sys

input = sys.stdin.readline


def dfs(cur_len, cur_idx):
    # base condition 1
    if cur_len == M:
        setSeqs.add(tuple(seq))
        return

    for i in range(cur_idx, lenNums):
        seq.append(nums[i])
        dfs(cur_len + 1, i)
        seq.pop()


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    nums = [*sorted(map(int, input().rstrip().split()))]
    seq = []
    lenNums = len(nums)
    setSeqs = set()
    # print(nums)
    for i in range(lenNums):
        seq.append(nums[i])
        dfs(1, i)
        seq.pop()

    for s in sorted(setSeqs):
        print(" ".join(map(str, s)))
