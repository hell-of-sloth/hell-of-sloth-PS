# 체감 난이도 3/10, 먼저 정렬 후 가장 큰 값과 가장 작은 값의 차이를 구하는 문제

def solve():
    N = int(input())
    
    boxes = list(map(int, input().split()))
    
    for _ in range(N):
        boxes.sort()
        boxes[0] += 1
        boxes[-1] -= 1
        
    return max(boxes) - min(boxes)

for t in range(1, 11):
    print('#{} {}'.format(t, solve()))