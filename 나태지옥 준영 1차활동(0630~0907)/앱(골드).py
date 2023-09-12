# 다이나믹프로그래밍 0-1 knapsack 활용 문제

import sys
N, M = map(int, sys.stdin.readline().split())
memory = [0] + list(map(int, sys.stdin.readline().split())) # 0번째 인덱스는 사용하지 않음, 메모리
cost = [0] + list(map(int, sys.stdin.readline().split())) # 0번째 인덱스는 사용하지 않음, 비용


def Application():
    global N, M, memory, cost
    
    dp = [[0 for _ in range(sum(cost)+1)] for _ in range(N+1)] # dp[i][j] : i번째 앱까지 고려했을 때, 비용이 j일 때 확보할 수 있는 최대 메모리
    result = sum(cost) # 최소 비용
    for i in range(1, N+1):
        for j in range(1, sum(cost)+1):
            
            now_mem = memory[i] # 현재 앱의 메모리
            now_cost = cost[i] # 현재 앱의 비용
            
            if now_cost <= j:
                dp[i][j] = max(dp[i-1][j-now_cost] + now_mem, dp[i-1][j]) # 현재 앱을 비활성화 시키거나, 활성화 시키거나, 더 큰 것 선택
            else:
                dp[i][j] = dp[i-1][j] # 비용이 부족하면 현재 앱 비활성화
            if dp[i][j] >= M:
                result = min(result, j) # 최소 비용 갱신
    print(result)
    
Application()        