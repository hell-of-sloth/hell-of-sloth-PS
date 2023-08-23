gomgomed = set()
ans = 0
for i in range(int(input())):
    name = input()
    if name =='ENTER':
        ans += len(gomgomed)
        gomgomed.clear()
    else:
        gomgomed.add(name)
ans += len(gomgomed)
print(ans)