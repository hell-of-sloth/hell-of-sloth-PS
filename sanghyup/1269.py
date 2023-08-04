m,n = map(int, input().split())
a= set()
b=set()
for i in input().split():
    a.add(i)
for i in input().split():
    b.add(i)
print(len(b-a)+len(a-b))