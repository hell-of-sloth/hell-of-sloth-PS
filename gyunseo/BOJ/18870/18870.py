import sys

from bisect import bisect_left
input = sys.stdin.readline

if __name__ == "__main__":
    N = int(input().strip())
    XList = [*map(int, input().strip().split())]
    XSortedSet = [*sorted(set(XList))]
    for x in XList:
        print(bisect_left(XSortedSet, x), end = " ")