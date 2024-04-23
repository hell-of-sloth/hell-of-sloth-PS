import sys

# sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def calculate():
    ret = 0
    for i in range(N - 1):
        ret += abs(permutatedA[i] - permutatedA[i + 1])
    return ret


def permutation(cur_len):
    # base condition 1
    if cur_len == N:
        global ans
        ans = max(ans, calculate())
        return

    for next_idx in range(N):
        if isUsed[next_idx]:
            continue
        isUsed[next_idx] = True
        permutatedA.append(A[next_idx])
        permutation(cur_len + 1)
        isUsed[next_idx] = False
        permutatedA.pop()


if __name__ == "__main__":
    N = int(input().rstrip())
    A = [*map(int, input().rstrip().split())]
    permutatedA = []
    isUsed = [False for _ in range(N)]
    ans = -1
    for i in range(N):
        isUsed[i] = True
        permutatedA.append(A[i])
        permutation(1)
        isUsed[i] = False
        permutatedA.pop()
    print(ans)
