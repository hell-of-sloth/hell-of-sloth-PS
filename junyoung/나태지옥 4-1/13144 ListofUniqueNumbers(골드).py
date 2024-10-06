import sys

N = int(sys.stdin.readline().rstrip())

L = tuple(map(int, sys.stdin.readline().split()))

count = 0
visit = [0 for _ in range(100001)]

end = 0

for i in range(N):
    while end < N:
        if visit[L[end]] == 1:
            break
        visit[L[end]] = 1
        end += 1
    count += end - i
    visit[L[i]] = 0
    
print(count)