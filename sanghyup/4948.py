import sys

input = sys.stdin.readline

count = [0]

def isPrime(n):
    if n == 0 or n==1:
        return False
    for i in range(2, int(n**0.5)+1):
        if not n%i:
            return False
    return True

n = 0
for i in range(1,123456*2+1):
    if isPrime(i):
        n+=1
    count.append(n)

while True:
    num = int(input())

    if num==0:
        break

    prime = count[2*num]-count[num]
    print(prime)
