import sys
import math

def isPrime(num =int): # 소수인지 판별해주는 함수
    if num == 1:
        return False
    for i in range(2, int(math.sqrt(num))+1):
        if num % i == 0:
            return False
    return True

def makePalindrome(num=int): # 팰린드롬 수를 만들어주는 함수
    num = str(num)
    strlenght = len(num)
    half = num[0:math.ceil(strlenght/2)] # 입력값의 절반 홀수일 경우 중간 값 포함 #num은 string임
    palindrome = half
    if strlenght % 2: # 홀수일 경우
        palindrome += half[-2::-1]
    else: # 짝수일 경우
        palindrome += half[::-1]
    return int(palindrome)

def getNextPalindrome(num=int): #다음 팰린드롬 수를 만들어주는 함수
    num = str(num)
    strlenght = len(num)
    half = num[0:math.ceil(strlenght/2)] # 입력값의 절반 홀수일 경우 중간 값 포함 #num은 string임
    is_nines = True
    for i in num: # 9로만 이루어져 있는지 확인
        if i != '9':
            is_nines = False
            break
    palindrome = ''
    if is_nines == True:
        palindrome = '1'
        for i in range(strlenght-1):
            palindrome += '0'
        palindrome += '1'
    if is_nines == False:
        if strlenght % 2: # 홀수일 경우
            half = str(int(half)+1)
            palindrome = half
            palindrome += half[-2::-1]
        else: # 짝수일 경우
            half = str(int(half)+1)
            palindrome = half
            palindrome += half[::-1]
    return int(palindrome)


N = int(sys.stdin.readline())
palindrome = makePalindrome(N)
while palindrome < N or isPrime(palindrome) == False:
    palindrome = getNextPalindrome(palindrome)
print (palindrome)



