# 체감 난이도 2/10, 완전탐색

T = int(input())

def solve():
    N, M = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    
    result = float('-inf')
    
    if N > M:
        N, M = M, N
        A, B = B, A
    
    for i in range(M-N+1):
        temp = 0
        for j in range(N):
            temp += A[j] * B[i+j]
        result = max(result, temp)
        
    return result

for t in range(T):
    print("#{}".format(t+1), solve())