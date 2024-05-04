import sys

input = sys.stdin.readline


def OOB(idx):
    if idx < 0 or idx >= N:
        return True
    return False


if __name__ == "__main__":
    N = int(input().rstrip())
    M = int(input().rstrip())
    parts = [*sorted(map(int, input().rstrip().split()))]
    ans = 0
    st = 0
    en = N - 1
    while not OOB(st) and not OOB(en) and st < en:
        tmp_sum = parts[st] + parts[en]
        if tmp_sum == M:
            ans += 1
            st += 1
            en -= 1
            continue
        if tmp_sum < M:
            st += 1
            continue
        if tmp_sum > M:
            en -= 1
            continue
    print(ans)
