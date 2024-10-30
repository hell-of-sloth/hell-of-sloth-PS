# 체감 난이도 2/10

def solve():
    N = int(input())
    tiles = [list(input()) for _ in range(8)]
    diag_tiles = list(zip(*tiles))
    
    cnt = 0
    
    for i in range(8):
        for j in range(8 - N + 1):
            if tiles[i][j:j + N] == tiles[i][j:j + N][::-1]:
                cnt += 1
            if diag_tiles[i][j:j + N] == diag_tiles[i][j:j + N][::-1]:
                cnt += 1
                
    return cnt

for t in range(10):
    print("#{} {}".format(t + 1, solve()))