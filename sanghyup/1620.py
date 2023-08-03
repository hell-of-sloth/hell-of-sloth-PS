import sys
input = sys.stdin.readline

M, N =  map(int, input().split())

pokemons = {}
values = {}
for i in range(M):
    poke = input().strip()
    pokemons[poke] = i+1
    values[i+1] = poke
for i in range(N):
    q = input().strip()
    if str.isdigit(q):
        print(values[int(q)])
    else:
        print(pokemons[q])