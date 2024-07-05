import sys
from bisect import bisect_left, bisect_right
input = sys.stdin.readline

if __name__ == "__main__":
    ans = 0
    N, P = map(int, input().strip().split())
    board = [[] for _ in range(7)]
    for _ in range(N):
        posI, posJ = map(int, input().strip().split())
        # print(f"query nums: {posI}, {posJ}")
        updateAns = 0
        cacheHit = False
        rightInsertPos = bisect_right(board[posI], posJ)
        # print(f"right insert pos: {rightInsertPos}")
        leftInsertPos = bisect_left(board[posI], posJ)
        # print(f"left insert pos: {leftInsertPos}")
        numPosJ = rightInsertPos - leftInsertPos 
        # check cache hit
        if numPosJ == 0:
            updateAns += 1
        else:
            # cache hit!
            cacheHit = True

        # check if higher j pos exists
        if len(board[posI]) > rightInsertPos:
            # pop elements that are bigger than query j pos
            while board[posI] and board[posI][-1] > posJ:
                board[posI].pop()
                updateAns += 1

        if not cacheHit:
            board[posI].append(posJ)
        # print(board[posI])
        ans += updateAns
    print(ans)