from itertools import permutations
import sys

N, M = map(int, input().split())

num_list = list(map(int, input().split()))
    
per_list = list(permutations(num_list, M)) # permutations 함수 이용

per_list.sort()

for item in per_list:
    sys.stdout.write(' '.join(map(str, item)) + '\n')