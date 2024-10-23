# 체감 난이도 3/10, 다행히 보자마자 0-1 배낭문제라는 것을 직감, DP 사용
# 통과 -> 테케 출력 까먹지 말기


T = int(input().strip())

def solve():
    N, L = map(int, input().split())
    score_cal_list = []
    for _ in range(N):
        score, cal = map(int, input().split())
        score_cal_list.append((score, cal))
        
    dp = [[0]*(L+1) for _ in range(N)]
    
    for i in range(N):
        for j in range(1, L+1):
            # 핵심 코드 (0-1 배낭문제)
            if i == 0: # 첫 번째 행은 처음것 하나만 고려해서 가로로 쭉 채우기
                if score_cal_list[i][1] <= j:
                    dp[i][j] = score_cal_list[i][0]
            else: # 2번째 부터는 이전 행의 값과 비교해서 최대값 채우기 (이전행의 포인트값 vs 이전행 추가전 값 + 현재 포인트값)
                if score_cal_list[i][1] <= j:
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j-score_cal_list[i][1]] + score_cal_list[i][0])
                else:
                    dp[i][j] = dp[i-1][j]
                    
    return dp[N-1][L]

for t in range(1, T+1):
    result = solve()
    print('#{} {}'.format(t, result))
