# 체감 난이도 3/10, 일반적인 구현 문제, 겹치는 코드가 많다 주의


T = int(input())


def solve():
    H, W = map(int, input().split())
    
    M = [list(input()) for _ in range(H)]
    
    N = int(input())
    command = list(input())
    tank = [0, 0]
    
    for i in range(H):
        for j in range(W):
            if M[i][j] == '^' or M[i][j] == 'v' or M[i][j] == '<' or M[i][j] == '>':
                tank = [i, j]
                break
            
    for c in command:
        if c == 'U':
            M[tank[0]][tank[1]] = '^'
            if 0 <= tank[0] - 1 < H and M[tank[0] - 1][tank[1]] == '.':
                M[tank[0]][tank[1]] = '.'
                M[tank[0] - 1][tank[1]] = '^'
                tank[0] -= 1
        elif c == 'D':
            M[tank[0]][tank[1]] = 'v'
            if 0 <= tank[0] + 1 < H and M[tank[0] + 1][tank[1]] == '.':
                M[tank[0]][tank[1]] = '.'
                M[tank[0] + 1][tank[1]] = 'v'
                tank[0] += 1
        elif c == 'L':
            M[tank[0]][tank[1]] = '<'
            if 0 <= tank[1] - 1 < W and M[tank[0]][tank[1] - 1] == '.':
                M[tank[0]][tank[1]] = '.'
                M[tank[0]][tank[1] - 1] = '<'
                tank[1] -= 1
        elif c == 'R':
            M[tank[0]][tank[1]] = '>'
            if 0 <= tank[1] + 1 < W and M[tank[0]][tank[1] + 1] == '.':
                M[tank[0]][tank[1]] = '.'
                M[tank[0]][tank[1] + 1] = '>'
                tank[1] += 1
        elif c == 'S':
            if M[tank[0]][tank[1]] == '^':
                for i in range(tank[0], -1, -1):
                    if M[i][tank[1]] == '*':
                        M[i][tank[1]] = '.'
                        break
                    elif M[i][tank[1]] == '#':
                        break
            elif M[tank[0]][tank[1]] == 'v':
                for i in range(tank[0], H):
                    if M[i][tank[1]] == '*':
                        M[i][tank[1]] = '.'
                        break
                    elif M[i][tank[1]] == '#':
                        break
            elif M[tank[0]][tank[1]] == '<':
                for i in range(tank[1], -1, -1):
                    if M[tank[0]][i] == '*':
                        M[tank[0]][i] = '.'
                        break
                    elif M[tank[0]][i] == '#':
                        break
            elif M[tank[0]][tank[1]] == '>':
                for i in range(tank[1], W):
                    if M[tank[0]][i] == '*':
                        M[tank[0]][i] = '.'
                        break
                    elif M[tank[0]][i] == '#':
                        break
                    
    return M

for t in range(1, T+1):
    print('#{}'.format(t), end=' ')
    for m in solve():
        print(''.join(m))