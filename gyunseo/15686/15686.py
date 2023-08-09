import sys
from enum import Enum
from itertools import product, combinations
import math

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().rstrip().split())
matrix = [[*map(int, input().rstrip().split())] for _ in range(N)]


class Site(Enum):
    HOME = 1
    CHICKEN = 2


def get_distance(coords):
    coord1, coord2 = coords[0], coords[1]
    return abs(coord1[0] - coord2[0]) + abs(coord1[1] - coord2[1])


def get_coords(site):
    ret_coords = []
    for i in range(N):
        for j in range(N):
            if matrix[i][j] == site.value:
                ret_coords.append((i, j))

    return ret_coords


home_coords = get_coords(Site.HOME)
chicken_coords = get_coords(Site.CHICKEN)


def get_chicken_dist_sum(param_chicken_coords):
    ret_chicken_dist_sum = 0
    for home_coord in home_coords:
        chicken_dist = math.inf
        for chicken_coord in param_chicken_coords:
            tmp_chicken_dist = get_distance([home_coord, chicken_coord])
            if tmp_chicken_dist < chicken_dist:
                chicken_dist = tmp_chicken_dist

        ret_chicken_dist_sum += chicken_dist

    return ret_chicken_dist_sum


chicken_dist_sum = math.inf

for to_be_survived_chicken_num in range(1, M + 1):
    cases = [*combinations(chicken_coords, to_be_survived_chicken_num)]
    for case in cases:
        new_chicken_coords = [*case]
        new_chicken_dist_sum = get_chicken_dist_sum(new_chicken_coords)
        # print(f"{new_chicken_dist_sum}\n")
        if new_chicken_dist_sum < chicken_dist_sum:
            chicken_dist_sum = new_chicken_dist_sum

print(f"{chicken_dist_sum}\n")
