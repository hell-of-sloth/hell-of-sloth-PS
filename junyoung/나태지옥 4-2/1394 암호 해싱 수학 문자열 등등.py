# 체감 난이도 8/10, 빡대가리는 못 푸는 문제, 나는 빡대가리라 개어렵게 풀었다
# 딕셔너리 쓰는데 나는 일일이 다했다 그래서 시간 2400ms 정도 나오던데 이 코드는 400ms 정도 나온다 현타온다
# 키의 값의 순서를 dict를 이용해서 미리 저장
# 현재 자리 가중치를 이용하여 푼다고 한다 -> temp
# 뒤부터 계산해서 result에 더하기
# 현타온다

import sys

MOD = 900528
keys = sys.stdin.readline().strip()
pw_dict = dict()

for i in range(len(keys)):
    pw_dict[keys[i]] = i + 1

pw = sys.stdin.readline().strip()
result = 0
temp = 1

for i in range(len(pw)-1, -1, -1):
    ch = pw[i]

    result += pw_dict[ch] * temp
    temp *= len(keys)

    result %= MOD
    temp %= MOD

print(result)

# import sys

# # 입력
# keys = list(sys.stdin.readline().strip())
# pwd = sys.stdin.readline().strip()

# keys_len = len(keys)
# pwd_len = len(pwd)
# MOD = 900528

# # 지수 값을 미리 계산해서 저장 (빠른 계산을 위해)
# pow_values = [1] * pwd_len
# for i in range(1, pwd_len):
#     pow_values[i] = (pow_values[i-1] * keys_len) % MOD

# # 결과 계산
# result = 0
# for i in range(pwd_len - 1):
#     result = (result + pow_values[i+1]) % MOD

# # 비밀번호에 대한 계산
# last = 0
# for i, char in enumerate(pwd):
#     last = (last + keys.index(char) * pow_values[pwd_len - i - 1]) % MOD

# result = (result + last + 1) % MOD

# print(result)
