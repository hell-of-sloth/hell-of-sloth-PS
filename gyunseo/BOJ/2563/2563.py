import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

color_paper_num = int(input().rstrip())
color_paper_pos_list = [
    tuple(map(int, input().rstrip().split())) for _ in range(color_paper_num)
]

matrix = [[False for _ in range(101)] for __ in range(101)]

for pos in color_paper_pos_list:
    pos_x, pos_y = pos

    def mark_matrix():
        for y in range(pos_y, pos_y + 10):
            for x in range(pos_x, pos_x + 10):
                matrix[y][x] = True

    mark_matrix()

ans = 0
for y in range(101):
    for x in range(101):
        if matrix[y][x] == True:
            ans += 1

print(f"{ans}\n")
