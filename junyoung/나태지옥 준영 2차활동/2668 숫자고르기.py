import sys

N = int(sys.stdin.readline().strip())

ziphap = [0] # 입력 리스트

for i in range(N):
    ziphap.append(int(sys.stdin.readline().strip()))
    
result = []

def dfs(start, current, visited):
    if start == current: # 시작점과 현재 위치가 같을 때
        temp_result = visited[:]
        return temp_result # 현재까지의 방문 경로를 반환
    
    if current in visited: # 현재 위치가 방문한 곳일 때 (시작위치 x)
        return [] # 빈 리스트 반환
    
    return dfs(start, ziphap[current], visited + [current])
    
for i in range(1, N+1):
    if dfs(i, ziphap[i], [i]):
        result += dfs(i, ziphap[i], [i])
    
set_result = set(result) # 중복 제거
print(len(set_result))
real_result = sorted(list(set_result)) # 정렬

for i in list(real_result):
    print(i)