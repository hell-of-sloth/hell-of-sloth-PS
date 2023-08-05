a1, b1 = map(int, input().split())
a2, b2 = map(int, input().split())

a3 , b3 = a1*b2+a2*b1, b1*b2
a , b = a3, b3
while(a3%b3):
    a3, b3 = b3, a3%b3

print(a//b3, b//b3)
