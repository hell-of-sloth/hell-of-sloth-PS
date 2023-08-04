m, n = map(int, input().split())
a = set()
b = set()
for i in range(m):
    a.add(input())
for i in range(n):
    b.add(input())
c = a&b
print(len(c),*(sorted(list(c))), sep= '\n')