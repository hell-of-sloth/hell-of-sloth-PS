import sys
from collections import deque

n = int(sys.stdin.readline())
cards = deque([i+1 for i in range(n)])
count = 1
while n > 1:
    if count % 2:
        cards.popleft()
        n -= 1
    else:
        cards.append(cards.popleft())
    count += 1
print(cards[0])
