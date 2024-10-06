a_str = input()
b_str = input()

def LCS2(A, B): # LCS2
    
    dp = [[0] * (len(B) + 1) for _ in range(len(A) + 1)] # LCS 길이 저장
    str_dp = [[""] * (len(B) + 1) for _ in range(len(A) + 1)] # LCS 문자열 저장
    
    for i in range(1, len(A) + 1):
        for j in range(1, len(B) + 1):
            if A[i - 1] == B[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
                str_dp[i][j] = str_dp[i - 1][j - 1] + A[i - 1]
                
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                str_dp[i][j] = str_dp[i - 1][j] if len(str_dp[i - 1][j]) > len(str_dp[i][j - 1]) else str_dp[i][j - 1]
                
    return dp[len(A)][len(B)], str_dp[len(A)][len(B)]

print(LCS2(a_str, b_str)[0])
print(LCS2(a_str, b_str)[1])
    
# dp는 아직 잘 모르겠따...