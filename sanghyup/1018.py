r, c = map(int, input().split())

board = []

for i in range(r):
    board.append([i for i in input()])

ans = 64
for startr in range(0, r-8+1):
    for startc in range(0,c-8+1):
        count1 = 0
        count2 = 0
        for checkr in range(startr, startr+8):
            for checkc in range(startc, startc+8):
                startColor=board[startr][startc]
                if (checkc + checkr)%2:
                    if board[checkr][checkc] == startColor:
                        count1+=1
                    else:
                        count2+=1
                else:
                    if board[checkr][checkc] != startColor:
                        count1+=1
                    else:
                        count2+=1
        ans = min(ans, count1, count2)
print(ans)