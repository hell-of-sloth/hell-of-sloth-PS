def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
    
def yeehanggyesoo(N, K):
    return(factorial(N)//factorial(K)//factorial(N-K))

print(yeehanggyesoo(*map(int, input().split())))