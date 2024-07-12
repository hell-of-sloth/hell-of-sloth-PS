import sys

input = sys.stdin.readline

if __name__ == "__main__":
    MOD = 1000_000
    S = input().strip()
    dp = [[0 for __ in range(2)] for _ in range(len(S))]
    dp[0][0] = 1 if S[0] != "0" else 0
    validJoinString = ["11","12","13","14","15","16","17","18","19","21","22","23","24","25","26"]
    for i in range(1, len(S)):
        if S[i] == "0":
            # 이전 문자와 결합하지 않는 경우
            dp[i][0] = 0
            # 이전 문자와 결합하는 경우
            dp[i][1] = dp[i - 1][0] % MOD if S[i - 1] == '1' or S[i - 1] == '2' else 0
        else:
            tmpStr = S[i - 1] + S[i]
            # print(tmpStr)
            dp[i][0] = dp[i - 1][0] % MOD + dp[i - 1][1] % MOD
            dp[i][0] %= MOD
            if i == 1:
                dp[i][1] = 1 if tmpStr in validJoinString else 0
            else:
                dp[i][1] = dp[i - 2][0] % MOD + dp[i - 2][1] % MOD if tmpStr in validJoinString else 0
                dp[i][1] %= MOD
    # print(dp)
    print((dp[len(S) - 1][0] % MOD + dp[len(S) - 1][1] % MOD)% MOD)