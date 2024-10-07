N = int(input())

T = [0]
P = [0]

for i in range(N):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)
    
dp = [0] * (N+1)

for i in range(1, N+1):
    if i + T[i] <= N+1:
        dp[i] = P[i]
        for j in range(1, i): 
            if j + T[j] <= i:
                dp[i] = max(dp[i], dp[j] + P[i])
    
print(max(dp))