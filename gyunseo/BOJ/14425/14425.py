import sys

input = sys.stdin.readline

if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    S = set()
    ans = 0
    for _ in range(N):
        S.add(input().rstrip())
    for _ in range(M):
        if input().rstrip() in S:
            ans += 1
    print(ans)
