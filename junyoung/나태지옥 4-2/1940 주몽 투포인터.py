# 체감 난이도 3/10, 알고리즘 고민하다가 시간 다감
# 처음에 백트래킹인가? 했는데 그냥 정렬 후 투포인터로 풀면 되는 문제
# 백트래킹으로 도저히 시간이 줄어들 방법이 안보여서 다른 방법을 찾아봄 

import sys

N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())
materials = list(map(int, sys.stdin.readline().split()))

materials.sort()
l = 0
r = N-1
result = 0

while l < r:
    if materials[l] + materials[r] == M:
        result += 1
        l += 1
    elif materials[l] + materials[r] < M:
        l += 1
    else:
        r -= 1
print(result)