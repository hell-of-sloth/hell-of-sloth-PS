# 체감 난이도 1/10, 인덱스 기준으로 양 쪽 두 개의 값 중 가장 큰 값과의 차이를 구하는 문제


def solve():
    N = int(input())
    
    nums = list(map(int, input().split()))
    result = 0
    
    for i in range(2, len(nums)-2):
        max_num = 0
        for j in range(i-2, i+3):
            if j == i:
                continue
            if nums[j] > max_num:
                max_num = nums[j]
        
        if nums[i] > max_num:
            result += nums[i] - max_num
            
    return result

for t in range(1, 11):
    print('#{} {}'.format(t, solve()))