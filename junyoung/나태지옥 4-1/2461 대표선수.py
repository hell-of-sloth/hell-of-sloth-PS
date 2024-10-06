from sys import stdin

N, M = map(int, stdin.readline().split())

students = []

for i in range(N):
    for score in map(int, stdin.readline().split()):
        students.append((score, i)) # (점수, 반 번호)

# 점수를 정렬
students.sort()

# 투 포인터 사용
left = 0
right = 0
min_diff = float('inf') # 최소 차이
count = [0] * N # 각 반의 인원 수
check_classes = 0 # 현재까지 포함된 반의 수

for right in range(len(students)):
    # 현재 포인터가 가리키는 학생의 반 인원 수가 0이면 check_classes를 1 증가시킨다.
    if count[students[right][1]] == 0:
        check_classes += 1
    count[students[right][1]] += 1

    # 모든 반의 학생이 포함되었을 때
    while check_classes == N:
        min_diff = min(min_diff, students[right][0] - students[left][0])
        count[students[left][1]] -= 1
        if count[students[left][1]] == 0:
            check_classes -= 1
        left += 1
    
    right += 1

print(min_diff)
