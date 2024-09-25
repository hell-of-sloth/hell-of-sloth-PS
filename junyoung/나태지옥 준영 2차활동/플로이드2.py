import sys

n = int(sys.stdin.readline().strip())
m = int(sys.stdin.readline().strip())

buses_map = [[(0, 0)] * (n+1) for _ in range(n+1)]

for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    if buses_map[a][b][0] == 0:
        buses_map[a][b] = (c, b)
    else:
        buses_map[a][b] = (min(buses_map[a][b][0], c), b)
    
def Floyd():
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                if buses_map[i][k][0] and buses_map[k][j][0]:
                    if i == j:
                        continue                   
                    if buses_map[i][j][0] == 0:
                        buses_map[i][j] = (buses_map[i][k][0] + buses_map[k][j][0], k)
                    else:
                        if buses_map[i][j][0] > buses_map[i][k][0] + buses_map[k][j][0]:
                            buses_map[i][j] = (buses_map[i][k][0] + buses_map[k][j][0], k)
    
def route_maker(start, end):
    path = [start, end]
    while True:
        flag = False
        for i in range(0, len(path)-1):
            if buses_map[path[i]][path[i+1]][1] != path[i+1]:
                path.insert(i+1, buses_map[path[i]][path[i+1]][1])
                flag = True
                break
        if flag == False:
            break
    return path
                
Floyd()

for i in range(1, n+1):
    for j in range(1, n+1):
        print(buses_map[i][j][0], end=' ')
    print()

for i in range(1, n+1):
    for j in range(1, n+1):
        if buses_map[i][j][0] == 0:
            print(0)
        else:
            path = route_maker(i, j)
            print(len(path), *path)