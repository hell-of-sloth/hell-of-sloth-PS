N = int(input())

liquid = list(map(int, input().split()))
liquid.sort()

def two_liquid():
    global liquid, N
    
    start, end = 0, N-1 # 투 포인터 시작점, 끝점
    min_sum = float('inf')
    min_start, min_end = 0, 0 # 최소인 용액의 인덱스 저장

    while start < end:
        tmp_sum = liquid[start] + liquid[end]

        if abs(tmp_sum) < min_sum: # 최소값 갱신
            min_sum = abs(tmp_sum)
            min_start, min_end = start, end

        if tmp_sum < 0:
            start += 1
        else:
            end -= 1

    print(liquid[min_start], liquid[min_end])
    
two_liquid()