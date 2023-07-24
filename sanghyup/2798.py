n, target = map(int, input().split())

cards = list(map(int, input().split()))

maxnum = 0
for i in range(len(cards)-2):
    for j in range(i+1, len(cards)-1):
        for k in range(j+1, len(cards)):
            check = cards[i]+cards[j]+cards[k]
            if check <= target:
                maxnum = max(maxnum, check)

print(maxnum)