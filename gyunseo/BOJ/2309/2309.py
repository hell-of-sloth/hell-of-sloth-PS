import sys
from itertools import combinations

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

dwarf_heights = [int(input().rstrip()) for _ in range(9)]
# print(f"{dwarf_heights}\n")

for combination in combinations(dwarf_heights, 7):
    if sum(combination) == 100:
        for height in sorted(combination):
            print(f"{height}\n")

        break
