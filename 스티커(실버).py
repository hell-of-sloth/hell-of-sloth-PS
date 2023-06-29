T = int(input())
        
def DP():
    if n == 1:
        return max(sticker[0][0], sticker[1][0])
    else:
        sticker[0][1] = sticker[1][0] + sticker[0][1]
        sticker[1][1] = sticker[0][0] + sticker[1][1]
        
        for i in range(2, n):
            sticker[0][i] = max(sticker[1][i-1], sticker[1][i-2]) + sticker[0][i]
            sticker[1][i] = max(sticker[0][i-1], sticker[0][i-2]) + sticker[1][i]
        return max(sticker[0][n-1], sticker[1][n-1])
    
for i in range(T):
    sticker = []
    n = int(input())
    for j in range(2):
        sticker.append(list(map(int, input().split())))    
    print(DP())