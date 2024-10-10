# N 범위 100만 -> O(N)으로 풀어야 함
# 체감 난이도 1/10, 그냥 0 개수 1개수 세서 둘 중 작은 것 출력
# for 문 한번 돌면서 0, 1 개수 세서 작은 것 출력 -> 그리디라고 함


import sys

S = list(sys.stdin.readline().strip())

kinds = [0, 0]

num = S[0]

if num == '0':
    kinds[0] += 1
else:
    kinds[1] += 1

for i in range(1, len(S)):
    if num != S[i]:
        num = S[i]
        kinds[int(num)] += 1

print(min(kinds))