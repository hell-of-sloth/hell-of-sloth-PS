# 체감 난이도 3/10, 인풋이 많아 O(N*2) 안 됨
# 뒤에서 가장 큰 값부터 시작해서 그 값보다 작은 값이 나오면 그 차이만큼 더해주면 됨



def solve():
    N = int(input())
    cost = list(map(int, input().split()))
    
    result = 0
    max_cost = cost[-1]
    
    for i in range(N-1, -1, -1):
        if cost[i] < max_cost:
            result += max_cost - cost[i]
        else:
            max_cost = cost[i]
            
    return result

T = int(input())

for t in range(1, T+1):
    print('#{} {}'.format(t, solve()))
        
        