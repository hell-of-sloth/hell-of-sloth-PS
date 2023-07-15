devideby = 0
All = 0
for i in range(20):
    _, A, B = input().split()
    A = float(A)
    if B[0] == 'P':
        continue
    elif B[0] == 'A':
        if B[1]=='+':
            All+=A*4.5
        else:
            All+=A*4.0
    elif B[0] == 'B':
        if B[1]=='+':
            All+=A*3.5
        else:
            All+=A*3.0
    elif B[0] == 'C':
        if B[1]=='+':
            All+=A*2.5
        else:
            All+=A*2.0
    elif B[0] == 'D':
        if B[1]=='+':
            All+=A*1.5
        else:
            All+=A*1.0
    devideby+=A

print(All/devideby)