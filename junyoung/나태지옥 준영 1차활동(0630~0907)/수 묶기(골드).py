import sys

N = int(sys.stdin.readline().rstrip())

plus_list = [] # 1 제외 양의 정수
minus_list = [] # 음의 정수
one = 0 # 1의 개수

for i in range(N): # 입력
    num = int(sys.stdin.readline().rstrip())
    if num > 1:
        plus_list.append(num)
    elif num == 1:
        one += 1
    else:
        minus_list.append(num)
        
plus_list.sort(reverse=True) # 내림차순 정렬
minus_list.sort() # 오름차순 정렬

def Su_Mukki(): # 수 묶기
    global plus_list, minus_list, one
    
    result = one # 1의 개수 더하기
    
    plen = len(plus_list) # 양의 정수 리스트 길이
    mlen = len(minus_list) # 음의 정수 리스트 길이
    
    if plen == 0:       # 양의 정수가 없을 경우
        result += 0
    elif plen % 2 == 0: # 양의 정수가 짝수개일 경우
        for i in range(0, plen, 2):
            result += plus_list[i] * plus_list[i+1]
    else:               # 양의 정수가 홀수개일 경우
        result += plus_list.pop(plen-1)
        for i in range(0, plen-1, 2):
            result += plus_list[i] * plus_list[i+1]
            
    if mlen == 0:       # 음의 정수가 없을 경우
        result += 0
    elif mlen % 2 == 0: # 음의 정수가 짝수개일 경우
        for i in range(0, mlen, 2):
            result += minus_list[i] * minus_list[i+1]
    else:               # 음의 정수가 홀수개일 경우
        result += minus_list.pop(mlen-1)
        for i in range(0, mlen-1, 2):
            result += minus_list[i] * minus_list[i+1]
            
    return result

print(Su_Mukki())