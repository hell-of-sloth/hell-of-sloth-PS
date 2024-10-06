import sys

N, M = map(int, sys.stdin.readline().split())

fuel_map = []

for _ in range(N):
    fuel_map.append(list(map(int, sys.stdin.readline().split())))

min_fuel = 1000000 # 최소 연료값

def ship_move(n, m, fuel, direction): # n: 세로, m: 가로, fuel: 현재 연료값, direction: 이전 방향
    global min_fuel
    
    if n == N-1:
        min_fuel = min(min_fuel, fuel + fuel_map[n][m]) # 최소 연료값 갱신
        return
    
    # 이전 방향에 따라 다음 방향을 결정
    if direction == 'l':
        ship_move(n+1, m, fuel + fuel_map[n][m], 'd')
        if m+1 < M:
            ship_move(n+1, m+1, fuel + fuel_map[n][m], 'r')
    elif direction == 'd':
        if m-1 >= 0:
            ship_move(n+1, m-1, fuel + fuel_map[n][m], 'l')
        if m+1 < M:
            ship_move(n+1, m+1, fuel + fuel_map[n][m], 'r')
    elif direction == 'r':
        ship_move(n+1, m, fuel + fuel_map[n][m], 'd')
        if m-1 >= 0:
            ship_move(n+1, m-1, fuel + fuel_map[n][m], 'l')
    else:
        if m-1 >= 0:
            ship_move(n+1, m-1, fuel + fuel_map[n][m], 'l')
        ship_move(n+1, m, fuel + fuel_map[n][m], 'd')
        if m+1 < M:
            ship_move(n+1, m+1, fuel + fuel_map[n][m], 'r')
            
for i in range(M):
    ship_move(0, i, 0, 0)
    
print(min_fuel)