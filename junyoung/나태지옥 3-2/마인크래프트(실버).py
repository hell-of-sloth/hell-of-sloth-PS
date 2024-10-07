import sys

N, M, B = map(int, sys.stdin.readline().split())

minecraft = []

for _ in range(N):
    minecraft.append(list(map(int, sys.stdin.readline().split())))
    
answer = float('inf')
idx = 0

for target in range(257): # 0부터 256층까지 비교
    high, low = 0, 0

    # 한 칸씩 다 확인
    for i in range(N):
        for j in range(M):

            # 블록이 층수보다 더 크면
            if minecraft[i][j] >= target:
                high += minecraft[i][j] - target

            # 블록이 층수보다 더 작으면
            else:
                low += target - minecraft[i][j]

    if high + B >= low: # 인벤토리에 블록이 충분하면
        if low + (high * 2) <= answer:
            answer = low + (high * 2) # 최저 시간
            idx = target # 층수

print(answer, idx)