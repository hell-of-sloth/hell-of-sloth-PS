import sys

comb = [[0 for _ in range(11)] for _ in range(11)]

def combination(n, r):
    global comb
    if comb[n][r] != 0:
        return comb[n][r]
    if n == r or r == 0:
        comb[n][r] = 1
        return 1
    else:
        comb[n][r] =  combination(n-1, r-1) + combination(n-1, r)
        return comb[n][r]
    
T = int(sys.stdin.readline())


for _ in range(T):
    inputnum = int(sys.stdin.readline())
    answer = 1
   
            
