#n == 7일 경우를 생각 못함...

n = int(input())

if n == 4 or n==7:
    print(-1)
elif n%5 == 0:
    print(n//5)
elif n%5 == 1:
    print((n-2*3)//5+2)
elif n%5 == 2:
    print((n-4*3)//5+4)
elif n%5 == 3:
    print((n-1*3)//5+1)
elif n%5 == 4:
    print((n-3*3)//5+3)
