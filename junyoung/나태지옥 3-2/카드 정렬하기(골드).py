import sys
import heapq

N = int(sys.stdin.readline().rstrip())

cards = [int(sys.stdin.readline().rstrip()) for _ in range(N)]
heapq.heapify(cards)

def minimun():
    global cards
    
    temp = 0
    result = 0
    if len(cards) == 1:
        return result
    
    while len(cards) > 1:
        a = heapq.heappop(cards)
        b = heapq.heappop(cards)
        temp = a+b
        result += temp
        heapq.heappush(cards, temp)
        
    return result

print(minimun())