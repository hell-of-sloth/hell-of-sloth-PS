import sys

input = sys.stdin.readline
MAX = 100_000 * 10


def OOB(idx):
    if idx < 0 or idx >= lenV:
        return True
    return False


def get_partial_sum(st, en):
    if st == 0:
        return prefixSum[en]
    return prefixSum[en] - prefixSum[st - 1]


if __name__ == "__main__":
    N, S = map(int, input().rstrip().split())
    V = [*map(int, input().rstrip().split())]
    lenV = len(V)
    prefixSum = [0 for _ in range(lenV)]
    prefixSum[0] = V[0]
    for i in range(1, lenV):
        prefixSum[i] = V[i] + prefixSum[i - 1]
    e = 0
    minLen = MAX
    for s in range(lenV):
        while not OOB(e) and get_partial_sum(s, e) < S:
            e += 1
        if e == lenV:
            break
        minLen = min(minLen, e - s + 1)

    print(minLen if minLen < MAX else 0)
