N, S = map(int, input().split())

num_list = list(map(int, input().split()))

def What_Is_Min():
    global N, S, num_list
    
    start = 0   # 시작점
    end = 0     # 끝점
    sum = 0     # 부분합
    min = float('inf') # 최소 길이
    while True:
        if sum >= S: # 부분합이 S보다 크거나 같으면
            if min > end - start:
                min = end - start
            sum -= num_list[start]
            start += 1
        elif end == N: # 끝점이 N이면 종료
            break
        else: # 부분합이 S보다 작으면
            sum += num_list[end]
            end += 1
    if min == float('inf'):
        return 0
    else:
        return min
    
print(What_Is_Min())