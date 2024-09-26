import sys

H, W = map(int, sys.stdin.readline().split())

rain_block = list(map(int, sys.stdin.readline().split()))
rain = [0] * W


left = 0
right = 0

while right < W:
    if left == right:
        right += 1
        continue
    if left == right - 1:
        if rain_block[left] <= rain_block[right]:
            left = right
            right += 1
        elif rain_block[left] > rain_block[right]:
            right += 1
        continue
    
    if rain_block[left] <= rain_block[right]:
        for i in range(left+1, right):
            rain_amount = rain_block[left] - rain_block[i]
            if rain_amount > 0:
                rain[i] = rain_amount
        left = right
    elif rain_block[left] > rain_block[right]:
        for i in range(left+1, right):
            rain_amount = rain_block[right] - rain_block[i]
            if rain_amount > 0:
                rain[i] = rain_amount
    right += 1
    
print(sum(rain))
    