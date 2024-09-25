from heapq import heappush, heappop, heapify

def solution(operations):
    q1 = []
    q2 = []
    
    for com in operations:
        f, s = com.split()
        if f == "I":
            heappush(q1, -int(s))
            heappush(q2, int(s))
        elif f == "D":
            if not q1:
                continue
            if s == "-1":
                q1.remove(-heappop(q2))
                heapify(q1)
            elif s == "1":
                q2.remove(-heappop(q1))
                heapify(q2)
                
    if q1:
        return [-heappop(q1), heappop(q2)]
    else:
        return [0, 0]