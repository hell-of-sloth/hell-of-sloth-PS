import sys

K, N = map(int, sys.stdin.readline().split())

origin_lan = []

for i in range(K):
    origin_lan.append(int(sys.stdin.readline().rstrip()))
    
def binary_search(start, end): # 이진 탐색 이용
    while start <= end:
        mid = (start + end) // 2
        count = 0
        for i in origin_lan:
            count += i // mid
        if count >= N:
            start = mid + 1
        else:
            end = mid - 1
    return end

print(binary_search(1, max(origin_lan)))