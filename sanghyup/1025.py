import math

def isSquare(n):
    if math.sqrt(n).is_integer():
        return 1
    else:
        return 0

a, b = input().split()
a = int(a)
b = int(b)

table = []

for i in range(a):
    table.append([int(num) for num in input()])

maxnum = -1

for row in range(a):
    for col in range(b):
        for jumprow in range(-a+1, a):
            for jumpcol in range(-b+1, b):
                if jumpcol ==0 and jumprow==0:
                    if isSquare(table[row][col])==1:
                        maxnum = max(table[row][col], maxnum)
                else:
                    #a,b에서 시작해 범위에 벗어날때까지 list에 jumprow, jumpcol를 더한 좌표의 값 입력
                    start = [row,col]
                    value = []
                    while 0<=start[0]<a and 0<=start[1]<b:
                        value.append(str(table[start[0]][start[1]]))
                        start[0]+=jumprow
                        start[1]+=jumpcol
                    for i in range(1,len(value)+1):
                        test = int(''.join(value[0:i]))
                        if isSquare(test)==1:
                            maxnum = max(test, maxnum)
                            
print(maxnum)