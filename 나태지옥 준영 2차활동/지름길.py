import sys

N, D = map(int, sys.stdin.readline().split())

jirum = []

for _ in range(N):
    a, b, d = map(int, sys.stdin.readline().split())
    if (b-a) > d and b <= D:
        jirum.append((a, b, (b-a)-d))

jirum.sort(key=lambda x: (x[0], x[1]))
jirum_dp = [ i[2] for i in jirum ]

for i in range(1, len(jirum)):
    for j in range(i):
        if jirum[i][0] >= jirum[j][1]:
            jirum_dp[i] = max(jirum_dp[i], jirum_dp[j] + jirum[i][2])
if jirum:
    result = D - max(jirum_dp)
else:
    result = D
print(result)
        