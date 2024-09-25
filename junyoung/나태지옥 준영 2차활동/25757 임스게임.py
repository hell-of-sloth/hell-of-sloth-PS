import sys

N, game = sys.stdin.readline().split()

players = []

for _ in range(int(N)):
    players.append(sys.stdin.readline().rstrip())
    
set_players = set(players)

if game == 'Y':
    print(len(set_players))
elif game == 'F':
    print(len(set_players) // 2)
elif game == 'O':
    print(len(set_players) // 3)