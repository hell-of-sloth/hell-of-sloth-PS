def isPrime(n):
    if n == 0 or n==1:
        return False
    for i in range(2, int(n**0.5)+1):
        if not n%i:
            return False
    return True


def getNextPrime(n):
    while(not isPrime(n)):
        n+=1
    return n

for i in range(int(input())):
    print(getNextPrime(int(input())))
    