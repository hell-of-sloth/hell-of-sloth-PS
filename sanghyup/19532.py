a,b,c,d,e,f = map(int, input().split())

for i in range(-999, 1000):
    for j in range(-999, 1000):
        if i*a+j*b == c and i*d+j*e == f:
            print(i,j)
            exit()