a = int(input())
b = int(input())
a = (a//100)*100
if not a%b:
    print('00')
else:
    ans = str(b*(a//b+1))
    print(ans[-2::])