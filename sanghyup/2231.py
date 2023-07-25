N = int(input())

for i in range(1, N):
    check = i
    for j in str(i):
        check+= int(j)
    if check == N:
        print(i)
        exit()
print(0)
