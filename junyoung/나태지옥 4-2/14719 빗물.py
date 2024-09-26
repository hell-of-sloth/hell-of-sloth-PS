# 체감 난이도 4/10, 투 포인터로 해결, 반례를 찾느라 고생좀 했음

import sys

H, W = map(int, sys.stdin.readline().split())

rain_block = list(map(int, sys.stdin.readline().split()))
rain = [0] * W

# 투 포인터를 이용
left = 0
right = 0

# right가 W를 넘어가면 종료
while right < W:
    
    # left와 right가 같으면 right를 1 증가시키고 다시 시작
    if left == right:
        right += 1
        continue
    
    # left와 right가 1 차이가 나면 2차이가 나게 만들어줌
    if left == right - 1:
        # left가 right보다 작거나 같으면 left를 right로 옮김
        if rain_block[left] <= rain_block[right]:
            left = right
        right += 1
        continue
    
    # left와 right가 2 이상 차이가 나면 빗물을 채워줌
    if rain_block[left] <= rain_block[right]: # left가 right보다 작거나 같으면
        for i in range(left+1, right):
            rain_amount = rain_block[left] - rain_block[i] # left와 i 사이의 빗물의 양
            if rain_amount > 0 and rain[i] < rain_amount: # 빗물이 있고 더 많은 빗물이 들어갈 수 있으면
                rain[i] = rain_amount
        left = right
    elif rain_block[left] > rain_block[right]: # left가 right보다 크면
        for i in range(left+1, right):
            rain_amount = rain_block[right] - rain_block[i] # right와 i 사이의 빗물의 양
            if rain_amount > 0 and rain[i] < rain_amount: # 빗물이 있고 더 많은 빗물이 들어갈 수 있으면
                rain[i] = rain_amount
    right += 1
    
print(sum(rain))
    