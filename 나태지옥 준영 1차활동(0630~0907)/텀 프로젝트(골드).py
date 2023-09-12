import sys

sys.setrecursionlimit(10**6) # 재귀 깊이를 늘려준다.

def DFS(num): # DFS를 통해 사이클을 찾는다.
    global students, visited, cycle, count
    
    visited[num] = True # 방문 처리
    cycle.append(num) # 사이클에 추가
    
    next = students[num] # 다음 학생
    
    if visited[next]: # 이미 방문한 학생이라면
        if next in cycle: # 사이클에 포함된 학생이라면
            count -= len(cycle[cycle.index(next):]) # 사이클에 포함된 학생들을 제외한다.
        return
    else:
        DFS(next) # 다음 학생으로 이동

T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    n = int(sys.stdin.readline().rstrip())
    
    students = [0] + list(map(int, sys.stdin.readline().split()))
    
    visited = [False] * (n + 1) # 방문 여부를 저장하는 리스트
    count = n # 팀을 이루지 못하는 학생 수
    for i in range(1, n + 1):
        if not visited[i]:
            cycle = [] # 사이클을 저장하는 리스트
            DFS(i)
    print(count)