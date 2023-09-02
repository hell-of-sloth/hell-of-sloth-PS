N = int(input())

def Yeechinsu_Guhagi(): # 이친수(실버)
    odd = 1 # 홀수는 다음번에 짝수로 변함
    even = 0 # 짝수는 다음번에 홀수, 짝수로 2개가 됨
    for i in range(1, N):
        new_even = odd + even
        new_odd = even
        odd = new_odd
        even = new_even
        
    print(odd + even) # 응애
    
Yeechinsu_Guhagi() # 이친수 구하기