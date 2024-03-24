def solution(clothes):
    result = 1
    dick = dict()
    for item, section in clothes:
        if section not in dick:
            dick[section] = 1
        else:
            dick[section] += 1
            
    for num in list(dick.values()):
        result *= num+1
        
    return result-1