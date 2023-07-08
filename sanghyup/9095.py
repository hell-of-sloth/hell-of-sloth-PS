import sys

def answer(n):
    if n == 1:
        return 1
    elif n == 2:
        return 2
    elif n == 3:
        return 4

    return answer(n-1) + answer(n-2) + answer(n-3)

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    print(answer(n))