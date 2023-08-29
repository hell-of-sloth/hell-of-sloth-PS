import sys
import math

sys.setrecursionlimit(10**9)
# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
counsel_schedules = [tuple(map(int, input().rstrip().split())) for _ in range(N)] + [
    (math.inf, 0)
]
# print(f"{counsel_schedules}\n")
ans = 0
# 시작 일자 brute force
for i in range(N):
    cur_T, cur_P = counsel_schedules[i]
    next_i = i + cur_T
    if next_i > N:
        continue

    # initial condition passed
    def dfs(cur_i, cur_P_sum, to_be_earned_P):
        global ans
        # update sum with to be earned P
        updated_P_sum = cur_P_sum + to_be_earned_P
        # choose if this schedule gets picked or not
        if cur_i + 1 <= N:
            dfs(cur_i + 1, updated_P_sum, 0)
        # update get next_to_be_earned_P
        cur_T, next_to_be_earned_P = counsel_schedules[cur_i]
        # update next_i
        ni = cur_i + cur_T
        if ni > N:
            if updated_P_sum > ans:
                ans = updated_P_sum
            return
        dfs(ni, updated_P_sum, next_to_be_earned_P)

    dfs(next_i, 0, cur_P)

print(f"{ans}\n")
