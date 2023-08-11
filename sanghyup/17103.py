#에라토스테네스의 체
# 

primes = [1]*1000001
primes[0], primes[1]= 0, 0

for i in range(2,1000001):
    if primes[i] == 1:
        for j in range(i*2,1000001, i):
            primes[j] = 0

for i in range(int(input())):
    count = 0
    n = int(input())
    for i in range(2, n//2+1):
        if primes[i] and primes[n-i]:
            count+=1
    print(count)
