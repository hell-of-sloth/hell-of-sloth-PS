import sys # 시간 단축쓰

T = int(sys.stdin.readline().rstrip())

for _ in range(T): # T번 반복
    N = int(sys.stdin.readline().rstrip())
    grade = [] # 성적을 담을 리스트
    for _ in range(N):
        grade.append(tuple(map(int, sys.stdin.readline().split())))
    grade.sort(key=lambda x: x[0]) # 성적을 기준으로 오름차순 정렬
    min_num = grade[0][1] # 첫 번째 학생의 후순위 성적을 최솟값으로 설정
    count = 1
    for i in range(1, N):
        if grade[i][1] < min_num: # 후순위 성적이 최솟값보다 작으면
            min_num = grade[i][1]
            count += 1
    print(count)
    
    # 생각을 좀 하면 시간을 단축해서 풀 수 있는 문제