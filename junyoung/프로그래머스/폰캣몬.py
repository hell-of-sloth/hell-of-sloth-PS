def solution(nums):
    n = len(nums) // 2
    answer = 0
    
    L = len(set(nums))
    
    if L > n:
        answer = n
    else:
        answer = L
    
    return answer