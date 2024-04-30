import sys

input = sys.stdin.readline

if __name__ == "__main__":
    E, S, M = map(int, input().rstrip().split())
    N = 1
    while True:
        if (N - E) % 15 == 0 and (N - S) % 28 == 0 and (N - M) % 19 == 0:
            print(N)
            exit(0)
        N += 1
