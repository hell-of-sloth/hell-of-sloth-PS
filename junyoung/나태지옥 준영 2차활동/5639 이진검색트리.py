import sys

sys.setrecursionlimit(10**6) # 재귀 깊이 제한을 늘리기

nums = []

while True:
    try:
        nums.append(int(sys.stdin.readline().strip()))
    except:
        break
    
nums = tuple(nums) # 튜플로 변환하면 이 코드의 경우 70ms 정도 빨라짐
    
def postOrder(first, last): # 각 서브트리의 처음과 끝 인덱스를 받음
    global nums
    idx = 0
    
    if last - first == 1:
        print(nums[first])
        return
    
    for i in range(first + 1, last):
        if nums[i] > nums[first]:
            idx = i # 오른쪽 서브트리의 시작 인덱스
            break
    
    if idx == first + 1:
        postOrder(first + 1, last)
    elif idx == 0:
        postOrder(first + 1, last)
    else:
        postOrder(first + 1, idx)
        postOrder(idx, last)
    print(nums[first])
    
postOrder(0, len(nums))