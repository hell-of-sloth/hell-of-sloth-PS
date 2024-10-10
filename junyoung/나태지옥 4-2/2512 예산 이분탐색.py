# N이 10000이라서 O(n^2)이면 안됨
# 체감 난이도 1/10, 비슷한 문제 풀어본적 있어서 바로 이분탐색으로 풀었음
# 완전탐색으로 풀면 시간초과 각 -> 이분탐색으로 매개변수 서칭
# 이분탐색은 O(logn)이라서 시간복잡도가 훨씬 줄어듦

import sys

N = int(sys.stdin.readline().strip())
budgets = list(map(int, sys.stdin.readline().split()))
M = int(sys.stdin.readline().strip())

l = 1
r = max(budgets)
mid = (l + r) // 2
answer = 0

while l <= r:
    
    result = 0
    
    for budget in budgets:
        result += min(budget, mid)

    if result > M:
        r = mid - 1
    elif result < M:
        l = mid + 1
        answer = mid
    else:
        answer = mid
        break
    
    mid = (l + r) // 2
    
print(answer)