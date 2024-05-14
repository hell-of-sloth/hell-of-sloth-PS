import sys

input = sys.stdin.readline


def OOB(p, len_arr):
    if p < 0 or p >= len_arr:
        return True
    return False


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split())
    A = [*map(int, input().rstrip().split())]
    B = [*map(int, input().rstrip().split())]
    lenA = len(A)
    lenB = len(B)
    ans = []
    # print(A)
    # print(B)
    aPointer = bPointer = 0
    while not OOB(aPointer, lenA) or not OOB(bPointer, lenB):
        if OOB(aPointer, lenA):
            ans.append(B[bPointer])
            bPointer += 1
            continue
        if OOB(bPointer, lenB):
            ans.append(A[aPointer])
            aPointer += 1
            continue

        if A[aPointer] <= B[bPointer]:
            ans.append(A[aPointer])
            aPointer += 1
        else:
            ans.append(B[bPointer])
            bPointer += 1
    print(" ".join(map(str, ans)))
