n = int(input())
count = 0
check = 666
while True:
    if '666'in str(check):
        count+=1
    if count==n:
        print(check)
        break
    check+=1