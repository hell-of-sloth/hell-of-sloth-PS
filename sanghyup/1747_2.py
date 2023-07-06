import sys
import math

temp =0

def isPrimeFunc(num =int): # 소수인지 판별해주는 함수
    for i in range(2, int(math.sqrt(num))+1):
        if num % i == 0:
            return False
    return True

def makePalindrome(num=int): # 팰린드롬 수를 만들어주는 함수
    num = str(num)
    strlenght = len(num)
    num = num[0:math.ceil(strlenght/2)] # 입력값의 절반 홀수일 경우 중간 값 포함 #num은 string임
    palindrome = num
    if strlenght % 2: # 홀수일 경우
        palindrome += num[-2::-1]
    else: # 짝수일 경우
        palindrome += num[::-1]
    return int(palindrome)

def getNextPalindrome(num=int, temp):
    num = str(num)
    strlenght = len(num)
    num = num[0:math.ceil(strlenght/2)] # 입력값의 절반 홀수일 경우 중간 값 포함 #num은 string임
    is_nines = True
    for i in num:
        if i != '9':
            is_nines = False
            break
    


N = int(sys.stdin.readline())
print(makePalindrome(N))


