# 체감 난이도 1/10, 해시인거 알면 매우 쉬움
# Set로 삽입 삭제 시간을 단축
# 출력시간도 print 여러개 써야해서 sys.stdout.write 사용

import sys

n = int(sys.stdin.readline().strip())

S = set()

for _ in range(n):
    name, state = sys.stdin.readline().split()
    if state == 'enter':
        S.add(name)
    else:
        S.discard(name)

S = sorted(list(S), reverse=True)

for name in S:
    sys.stdout.write(name + '\n')