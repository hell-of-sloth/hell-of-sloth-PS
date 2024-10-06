L, P = map(int, input().split())

people = L * P
plist = list(map(int, input().split()))
for i in range(len(plist)):
    print(plist[i] - people, end=' ')