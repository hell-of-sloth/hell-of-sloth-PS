def isPrime(n):
    if n == 0 or n==1:
        return False
    for i in range(2, int(n**0.5)+1):
        if not n%i:
            return False
    return True

m, n = map(int, input().split())

for i in range(m,n+1):
    if isPrime(i):
        print(i)