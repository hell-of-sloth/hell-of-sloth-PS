'''
같은 원소가 
K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하려고
한다.

100000 이하의 양의 정수로 이루어진 길이가 
N인 수열이 주어진다.  이 수열에서 같은 정수를 
K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 
프로그램을 작성해보자.
'''


import sys

N, K = map(int, sys.stdin.readline().split())

visited = [0] * 100001

L = list(map(int, sys.stdin.readline().split()))

end = 0

max_range = 0

for start in range(N):
    while end < N and visited[L[end]] < K:
        visited[L[end]] += 1
        end += 1
    max_range = max(max_range, end - start)
    visited[L[start]] -= 1
    
print(max_range)