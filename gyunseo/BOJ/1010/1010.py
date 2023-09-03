import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write


T = int(input().rstrip())
dp = [None] * 31
dp[0] = 1
dp[1] = 1
dp[2] = 2

for i in range(3, 31):
    dp[i] = dp[i - 1] * i


# print(f"{dp}\n")
while T:
    N, M = map(int, input().rstrip().split())

    # print(f"{N}, {M}\n")

    def combinate(n, r):
        return dp[n] // (dp[n - r] * dp[r])

    print(f"{combinate(M, N)}\n")
    T -= 1
