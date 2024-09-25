from sys import stdin

N, M, L = map(int, stdin.readline().split())
rest_area = sorted(list(map(int, stdin.readline().split())))
rest_area = [0] + rest_area + [L]

start = 1
end = L

while start <= end:
    mid = (start + end) // 2
    cnt = 0
    for i in range(1, len(rest_area)):
        distance = rest_area[i] - rest_area[i-1]
        if distance > mid:
            cnt += (distance - 1) // mid

    if cnt > M:
        start = mid + 1
    else:
        end = mid - 1

print(start)