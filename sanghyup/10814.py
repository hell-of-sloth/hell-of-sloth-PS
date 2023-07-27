n = int(input())

a = []
for _ in range(n):
    a.append((input().split()))

a.sort(key=lambda x: int(x[0]))

for i in a:
    print(*i)