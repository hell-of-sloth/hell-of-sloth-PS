# 체감 난이도 3/10, 누적합 문제 예제 적응해보기
# 슬라이딩 윈도우 기법에 사용하면 좋은 듯
# DP랑 차이가 뭐지? -> DP는 누적합을 저장해놓는 것이고, 슬라이딩 윈도우는 누적합을 이용해서 최대값을 찾는 것
# 이게 맞나?

def solve():
    N, K, B = map(int, input().split())
    broken = set([int(input()) for _ in range(B)])
    
    dp = [0] * (N + 1)
    max_len = 0
    
    for i in range(1, N + 1):
        if i in broken:
            dp[i] = dp[i - 1]
        else:
            dp[i] = dp[i - 1] + 1
            
    
    for i in range(K, N + 1):
        max_len = max(max_len, dp[i] - dp[i - K])
        
    return K - max_len

print(solve())
            