import sys

def Palindrome(num, a, b): # a부터 b까지 팰린드롬인지 확인
    global palindrome_dp # 팰린드롬인지 확인한 부분을 저장하는 dp
    
    if a == b: # 길이가 1인 경우
        return True
    
    na, nb = a, b # a와 b를 각각 na와 nb에 저장
    while na < nb:
        if num[na] != num[nb]: # 팰린드롬이 아닌 경우
            return False
        if palindrome_dp[na][nb] == 1: # 이미 팰린드롬인 경우
            Palindrome_Maker(a, b) # 팰린드롬인 부분을 dp에 저장
            return True
        na += 1
        nb -= 1
    Palindrome_Maker(a, b) # 팰린드롬인 부분을 dp에 저장
    return True

def Palindrome_Maker(a, b): # a부터 b까지 팰린드롬인 부분을 dp에 저장
    global palindrome_dp
    
    while a < b: # a부터 b까지 팰린드롬인 부분을 dp에 저장
        if palindrome_dp[a][b] == 1: # 이미 팰린드롬인 경우 끝내기
            return
        palindrome_dp[a][b] = 1
        a += 1
        b -= 1

N = int(sys.stdin.readline().rstrip())

num = list(map(int, sys.stdin.readline().split()))

M = int(sys.stdin.readline().rstrip())

palindrome_dp = [[0] * N for _ in range(N)] # 팰린드롬인지 확인한 부분을 저장하는 dp, 2차원 배열로 접근 시간을 줄임
for i in range(N):
    palindrome_dp[i][i] = 1

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    a -= 1
    b -= 1
    if Palindrome(num, a, b):
        sys.stdout.write('1\n')
    else:
        sys.stdout.write('0\n')