import sys
import itertools

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().rstrip().split())

matrix = [list(map(int, input().rstrip().split())) for _ in range(N)]
virus_positions = []
blank_poistions = []
for i in range(N):
    for j in range(M):
        if matrix[i][j] == 2:
            virus_positions.append((i, j))
        elif matrix[i][j] == 0:
            blank_poistions.append((i, j))


def make_blank_wall(pos):
    matrix[pos[0]][pos[1]] = 1


def make_wall_blank(pos):
    matrix[pos[0]][pos[1]] = 0


def get_blank_count():
    ret = 0
    for i in range(N):
        for j in range(M):
            if matrix[i][j] == 0:
                ret += 1
    return ret


directions = [(1, 0), (-1, 0), (0, -1), (0, 1)]


def init_matrix():
    for i in range(N):
        for j in range(M):
            if matrix[i][j] == 2:
                if (i, j) not in virus_positions:
                    matrix[i][j] = 0


def spread_virus(start):
    for direction in directions:
        ni = direction[0] + start[0]
        nj = direction[1] + start[1]

        if ni < 0 or ni >= N:
            continue

        if nj < 0 or nj >= M:
            continue

        if matrix[ni][nj] == 1 or matrix[ni][nj] == 2:
            continue

        matrix[ni][nj] = 2
        spread_virus((ni, nj))


possible_cases = itertools.combinations(blank_poistions, 3)
ans = -1
for case in possible_cases:
    a, b, c = case
    # matrix 초기화 해야 됨
    init_matrix()
    make_blank_wall(a)
    make_blank_wall(b)
    make_blank_wall(c)
    for virus_pos in virus_positions:
        spread_virus(virus_pos)
    tmp_ans = get_blank_count()
    ans = ans if ans > tmp_ans else tmp_ans

    make_wall_blank(a)
    make_wall_blank(b)
    make_wall_blank(c)

print(f"{ans}\n")
