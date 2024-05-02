import sys
import math

input = sys.stdin.readline


def is_r2_in_r1(x1, y1, r1, x2, y2, r2):
    if x2 < x1 - r1 or x2 > x1 + r2:
        return False
    if y2 < y1 - r1 or y2 > y1 + r1:
        return False
    return True


if __name__ == "__main__":

    T = int(input().rstrip())
    # 두 원의 교점은 2개, 1개, 0개밖에 없음...
    # 고등학교 기초 수학인데 와 이걸 까먹네?
    for _ in range(T):
        x1, y1, r1, x2, y2, r2 = map(int, input().rstrip().split())
        dSquare = (x1 - x2) ** 2 + (y1 - y2) ** 2
        r1Plusr2Square = (r1 + r2) ** 2
        if dSquare == 0 and r1 == r2:
            print(-1)
        elif r1Plusr2Square < dSquare:
            print(0)
        elif r1Plusr2Square == dSquare:
            print(1)
        elif r1Plusr2Square > dSquare:
            if r1 < r2:
                x1, x2 = x2, x1
                y1, y2 = y2, y1
                r1, r2 = r2, r1
            if not is_r2_in_r1(x1, y1, r1, x2, y2, r2):
                print(2)
                continue

            r1Minusr2Square = (r1 - r2) ** 2
            if r1Minusr2Square == dSquare:
                print(1)
                continue
            elif r1Minusr2Square < dSquare:
                print(2)
            else:
                print(0)
