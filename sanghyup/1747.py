def isPrime(num): 
    for i in range(2, int(round(num**0.5)+1)):
        if num % i == 0:
            return False
    return True

def ans(N):
    if N ==1:
        return 2
    result =0
    for i in range(N, 1003002):
        if str(i) == str(i)[::-1]: 
            if isPrime(i) == True: 
                result = i
                break
    return result
N = int(input())
print(ans(N))