import sys

N, M = map(int, sys.stdin.readline().split())

team_mem_hash = {}
mem_team_hash = {}

for _ in range(N):
    team_name = sys.stdin.readline().strip()
    team_number = int(sys.stdin.readline().strip())
    if team_name not in team_mem_hash:
        team_mem_hash[team_name] = []
    for i in range(team_number):
        mem = sys.stdin.readline().strip()
        team_mem_hash[team_name].append(mem)
        mem_team_hash[mem] = team_name
        
for _ in range(M):
    name = sys.stdin.readline().strip()
    q = int(sys.stdin.readline().strip())
    if q:
        print(mem_team_hash[name])
    else:
        for team_name in sorted(team_mem_hash[name]):
            print(team_name)