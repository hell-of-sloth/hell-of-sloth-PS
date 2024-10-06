L = int(input())
STR = input()
ans = 0

for i in range(L):
    ans += (ord(STR[i])-96) * (31 ** i) #아스키 코드 값을 돌려주는 ord함수
print(ans % 1234567891)