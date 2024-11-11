# 체감 난이도 6/10, dp ? 문제
# 처음엔 dfs로 시도 -> 시간 초과
# 시간초과가 꽤 빡셈
# dp로 시도 -> dp 리스트 * 100001 로 해보려 했으나 뭔가 시간더 줄일 수 있을 것 같았음
# dp 리스트를 set으로 바꿔서 시도 -> 성공
# set안에 있는 요소를 1개씩 다 더해서 다시 넣기 방법

def solve():
    N = int(input())
    scores = list(map(int, input().split()))
    
    score_set = set([0])
    
    for score in scores:
        temp_set = set()
        for s in score_set:
            temp_set.add(s + score)
        score_set.update(temp_set)
        
    return len(score_set)
    
    
T = int(input())

for t in range(1, T+1):
    print('#{} {}'.format(t, solve()))
