# 체감 난이도 3/10, 스택인줄 알았는데 조건을 생각하기가 좀 까다로웠음
# left_flag / right_flag 생각 안났으면 꽤 걸렸을 지도 (19분 컷)
# 스택을 이용해서 O(N)으로 바로 품, 56ms
# 근데 생각해보니 stk 필요 없겠는데?
# 스택 뺀 결과 -> 동작 O, 그 만큼 시간도 줄어듦, 52ms
# 스택 문제인데 스택이 읎다


import sys

L = sys.stdin.readline().strip()

stick = 0 # 현재 막대기 수
result = 0

left_flag = False # 바로 전에 ( 이 들어 왔는지
right_flag = False # 바로 전에 ) 이 들어 왔는지

for i in L:
    if i == '(':
        right_flag = False
        if left_flag == True:   # 막대가 있다는 것을 확인, 막대기 수랑 결과 같이 1올려줌
            stick += 1
            result += 1
        else:                   # 레이져 후 또는 시작후 바로 ( 나오면
            left_flag = True
            
    else:
        left_flag = False
        if right_flag == True:  # 막대 끝, 막대기만 1빼줌
            stick -= 1
        else:                   # 레이저 있음, 막대기 수만큼 결과에 더해주기
            result += stick
            right_flag = True
            
print(result)

# import sys

# L = sys.stdin.readline().strip()
# stk = []

# stick = 0 # 현재 막대기 수
# result = 0

# left_flag = False # 바로 전에 ( 이 들어 왔는지
# right_flag = False # 바로 전에 ) 이 들어 왔는지

# for i in L:
#     if i == '(':
#         stk.append(i)
#         right_flag = False
#         if left_flag == True:   # 막대가 있다는 것을 확인, 막대기 수랑 결과 같이 1올려줌
#             stick += 1
#             result += 1
#         else:                   # 레이져 후 또는 시작후 바로 ( 나오면
#             left_flag = True
            
#     else:
#         stk.pop() # 쌍이 완성되면 pop
#         left_flag = False
#         if right_flag == True:  # 막대 끝, 막대기만 1빼줌
#             stick -= 1
#         else:                   # 레이저 있음, 막대기 수만큼 결과에 더해주기
#             result += stick
#             right_flag = True
            
# print(result)