m, n = map(int, input().split())
s = set()
count = 0
for i in range(m):
    s.add(input())
for i in range(n):
    if input() in s:
        count+=1
print(count)