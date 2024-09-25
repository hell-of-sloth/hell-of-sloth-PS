def solution(participant, completion):
    answer = ''
    
    dic = dict()
    for p in participant:
        if p not in dic:
            dic[p] = 1
        else:
            dic[p] += 1
            
    for c in completion:
        dic[c] -= 1
        
    for i in list(dic.keys()):
        if dic[i] > 0:
            return i
