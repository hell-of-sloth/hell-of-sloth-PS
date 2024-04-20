import sys

input = sys.stdin.readline
MAX_LEN = 50
MAX_VAL = 100


def get_S():
    ret = 0
    for i in range(N):
        ret += A[i] * B[i]
    return ret


if __name__ == "__main__":
    N = int(input().rstrip())
    A = [*map(int, input().rstrip().split())]
    B = [*map(int, input().rstrip().split())]
    # print("A: ", A)
    # print("B: ", B)
    # A가 B의 계수라고 생각하면 된다
    A.sort(key=lambda x: -x)
    B.sort()
    print(get_S())
