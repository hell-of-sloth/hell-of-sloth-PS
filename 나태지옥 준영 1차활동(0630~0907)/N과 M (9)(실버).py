from itertools import permutations # 순열 이용

N, M = map(int, input().split())

num_list = list(map(int, input().split()))
result_list = list(permutations(num_list, M))

result = set(result_list)
list_result = list(result)
list_result.sort()

for item in list_result:
    print(*item)