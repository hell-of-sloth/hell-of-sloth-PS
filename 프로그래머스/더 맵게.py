import heapq

def solution(scoville, K):
    
    answer = 0
    
    heapq.heapify(scoville)
    
    while len(scoville) > 0:
        min_sco = heapq.heappop(scoville)
        
        if min_sco >= K:
            return answer
        elif scoville:
            sec_min_sco = heapq.heappop(scoville)
            new_sco = min_sco + (sec_min_sco * 2)
            heapq.heappush(scoville, new_sco)
            answer += 1
            
    return -1