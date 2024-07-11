import sys

input = sys.stdin.readline

def OOB(idx):
    if idx < 0 or idx >= M:
        return True
    return False

if __name__ == "__main__":
    N = int(input().strip())
    M = int(input().strip())
    S = input().strip()
    ans = 0
    windowSize = 2 * N + 1
    # print(N, M, S)
    start = end = 0
    alternatingCount = 0

    for start in range(M):
        isStartLegit = True if S[start] == "I" else False
        while not OOB(end) and end - start + 1 < windowSize:
            # 앞으로 새로 볼 문자가 alternating하는지 카운트
            if not OOB(end + 1) and S[end + 1] != S[end]:
                alternatingCount += 1
            end += 1
        # windowSize만큼 창문이 커졌으면
        # 이제 조건을 충족했는지 까볼 차례
        # 먼저 막 문자도 legit한지
        if OOB(end):
            break
        isEndLegit = True if S[end] == "I" else False
        # print(S[start:end + 1])
        # print(f"alternating count so far: {alternatingCount}, is legit start: {isStartLegit}, is legit end: {isEndLegit}")
        if isStartLegit and isEndLegit and alternatingCount == 2 * N:
            ans += 1
        # window에서 하나 pop하는데, alternating한걸 pop하면 count 하나 까기
        if not OOB(start + 1) and S[start] != S[start + 1]:
            alternatingCount -= 1
    print(ans)
            
            
        