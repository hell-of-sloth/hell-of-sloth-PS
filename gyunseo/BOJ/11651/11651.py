import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline

print = sys.stdout.write

N = int(input().rstrip())

coords = [tuple(map(int, input().rstrip().split())) for _ in range(N)]

# print(f"{coords}\n")
# print(f"{sorted(coords, key= lambda x: (x[1], x[0]))}\n")

for x, y in sorted(coords, key=lambda x: (x[1], x[0])):
    print(f"{x} {y}\n")
