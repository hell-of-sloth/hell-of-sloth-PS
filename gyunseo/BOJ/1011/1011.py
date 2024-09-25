T = int(input())

for i in range(T):
    x, y = map(int, input().split())  # 출발 및 도착 지점

    d = y - x  # 거리

    n = 0

    while True:
        if d <= n * (n + 1):
            break
        n += 1

    # 총 이동 거리가 n의 제곱보다 작거나 같을 때
    if d <= n**2:
        print(n * 2 - 1)

    # 총 이동 거리가 n의 제곱보다 클 때
    else:
        print(n * 2)
