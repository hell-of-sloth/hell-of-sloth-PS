import sys
import math

sys.setrecursionlimit(10**9)
# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
matrix = [[*map(int, input().rstrip().split())] for _ in range(N)]
is_flooded = [[False for __ in range(N)] for _ in range(N)]
safe_area_cnt = -math.inf
# 상, 하, 좌, 우
directions = [(1, 0), (-1, 0), (0, -1), (0, 1)]
for rain_height in range(101):

    def get_safe_area_cnt():
        ret_safe_area_cnt = 0

        def init_is_flooded():
            for i in range(N):
                for j in range(N):
                    is_flooded[i][j] = False

        def make_it_rain():
            for i in range(N):
                for j in range(N):
                    if matrix[i][j] <= rain_height:
                        is_flooded[i][j] = True

        def get_safe_area_coords():
            ret_coords = []
            for i in range(N):
                for j in range(N):
                    if is_flooded[i][j] == False:
                        ret_coords.append((i, j))
            return ret_coords

        # 방문하면 flooded됐다고 마크하기
        def dfs(cur):
            i, j = cur
            is_flooded[i][j] = True

            for direction in directions:
                ni, nj = i + direction[0], j + direction[1]
                if ni < 0 or ni >= N:
                    continue
                if nj < 0 or nj >= N:
                    continue

                if is_flooded[ni][nj]:
                    continue

                dfs((ni, nj))

        init_is_flooded()
        make_it_rain()
        safe_area_coords = get_safe_area_coords()
        for safe_area_coord in safe_area_coords:
            ci, cj = safe_area_coord
            if is_flooded[ci][cj]:
                continue
            dfs(safe_area_coord)
            # print(f"dfs하고나서, 현재 is_flooded 상태: {is_flooded}\n")
            ret_safe_area_cnt += 1

        return ret_safe_area_cnt

    new_safe_area_cnt = get_safe_area_cnt()
    # print(f"강수량: {rain_height}, safe_area_cnt: {new_safe_area_cnt}\n")
    if safe_area_cnt < new_safe_area_cnt:
        safe_area_cnt = new_safe_area_cnt

print(f"{safe_area_cnt}\n")
