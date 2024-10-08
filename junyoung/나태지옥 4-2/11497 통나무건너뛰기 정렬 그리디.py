# 체감 난이도 3/10, 처음에는 딱 안 떠올랐는데 정렬해보니까 딱 보였음
# deque을 이용해서 작은거부터 앞 뒤 앞 뒤 넣으면 됨
# deque은 인덱스 접근 시 양 끝은 O(1)이지만 중간은 O(n)이라서 시간복잡도가 높아질 수 있음 -> 이거 주의
# 그래서 양 끝값을 이용해 답 계산후 넣어 줬음
# 테케가 여러개라서 sys.stdout.write로 출력해줬음

import sys
from collections import deque

T = int(sys.stdin.readline().strip())

for _ in range(T):
    N = int(sys.stdin.readline().strip())
    logs = list(map(int, sys.stdin.readline().split()))
    logs.sort()
    
    result = deque()
    ans = 0

    for i in range(N):
        if i % 2 == 0:
            if i > 0:
                ans = max(ans, abs(logs[i] - result[-1]))
            result.append(logs[i])
        else:
            ans = max(ans, abs(logs[i] - result[0]))
            result.appendleft(logs[i])
    
    ans = max(ans, abs(result[0] - result[-1]))
    
    sys.stdout.write(str(ans) + '\n')
           