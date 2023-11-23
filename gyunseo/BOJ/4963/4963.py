import sys
import itertools

sys.setrecursionlimit(100000)
# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

input_iteration_trigger = True


directions = list(itertools.product([0, -1, 1], [0, -1, 1]))
directions = list(filter(lambda x: x != (0, 0), directions))


while input_iteration_trigger:
    w, h = map(int, input().rstrip().split())

    if w == 0 and h == 0:
        input_iteration_trigger = False
        continue

    matrix = [list(map(int, input().rstrip().split())) for _ in range(h)]

    def get_land_positions():
        ret_positions = []
        for i in range(h):
            for j in range(w):
                if matrix[i][j] == 1:
                    ret_positions.append((i, j))

        return ret_positions

    def get_islands_count():
        is_visited = [[False for _ in range(w)] for _ in range(h)]
        islands_count = 0

        def dfs(start):
            ci, cj = start

            if is_visited[ci][cj] == True:
                return

            is_visited[ci][cj] = True

            for direction in directions:
                ni, nj = ci + direction[0], cj + direction[1]
                if ni < 0 or ni >= h:
                    continue
                if nj < 0 or nj >= w:
                    continue
                if matrix[ni][nj] == 0:
                    continue

                dfs((ni, nj))

        for land_position in land_positions:
            if is_visited[land_position[0]][land_position[1]]:
                continue
            dfs(land_position)
            islands_count += 1

        return islands_count

    land_positions = get_land_positions()
    print(f"{get_islands_count()}\n")
