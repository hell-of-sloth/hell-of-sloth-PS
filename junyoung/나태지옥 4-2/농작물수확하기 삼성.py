# 체감 난이도 2/10, 리스트 슬라이싱으로 쉽게 풀 수 있음

T = int(input())

def solve():
    N = int(input())
    farm = [list(map(int, input())) for _ in range(N)]
    
    result = 0
    
    center = N // 2
    for i in range((N // 2) + 1):
        if i == 0:
            result += sum(farm[center])
        else:
            result += sum(farm[center - i][i:N-i])
            result += sum(farm[center + i][i:N-i])
    
    return result
        
    
for t in range(1, T+1):
    print('#{} {}'.format(t, solve()))