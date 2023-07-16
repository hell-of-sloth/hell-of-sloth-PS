n = int(input())
for i in range((-n+1), n):
    print(' '*(abs(i)), end='')
    print('*'*(2*n - (abs(2*i)+1)))