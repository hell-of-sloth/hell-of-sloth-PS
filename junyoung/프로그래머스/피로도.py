from itertools import permutations

def solution(k, dungeons):
    
    per = list(permutations(dungeons, len(dungeons)))
    
    max_value = 0
    
    for route in per:
        temp_k = k
        val = 0
        for i in route:
            if temp_k <= 0:
                max_value = max(max_value, val)
                break
            if temp_k >= i[0]:
                temp_k -= i[1]
                val += 1
                
        max_value = max(max_value, val)
        
    return max_value

print(solution(80, [[80,20],[50,40],[30,10]]))