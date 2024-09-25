def solution(prices):
    
    answer = [0 for _ in range(len(prices))]
    
    stk = [] # 인덱스 저장
    
    i = 0 # 현재 인덱스, 마지막 while에서 사용하기 위해 선언
    
    for i in range(len(prices)):
        if i == 0:
            stk.append(i)
            continue
        
        while stk and prices[stk[-1]] > prices[i]:
            j = stk.pop()
            answer[j] = i - j
        stk.append(i)
    
    while stk:
        j = stk.pop()
        answer[j] = i - j
    
    return answer