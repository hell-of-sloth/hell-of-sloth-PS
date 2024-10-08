# 체감 난이도 2/10, set 쓰면 풀림
# print 많으면 sys.stdout.write고려 혹은 다른 방법 찾기

import sys

T = int(sys.stdin.readline().strip())

for _ in range(T):
    N = int(sys.stdin.readline().strip())
    note_1 = set(map(int, sys.stdin.readline().split()))
    M = int(sys.stdin.readline().strip())
    note_2 = list(map(int, sys.stdin.readline().split()))

    for i in note_2:
        if i in note_1:
            sys.stdout.write('1\n')
        else:
            sys.stdout.write('0\n')