s= []
for i in range(5):
    s.append(int(input()))
s.sort()
print(int(sum(s)/len(s)), s[len(s)//2])