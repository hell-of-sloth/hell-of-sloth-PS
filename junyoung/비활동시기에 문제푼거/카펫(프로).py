def solution(brown, yellow):
    answer = []
    
    half = brown // 2
    for i in range(1,half-2):
        j = (half-2) - i
        if i * j == yellow:
            answer = [j+2,i+2]
            break
        
    return answer