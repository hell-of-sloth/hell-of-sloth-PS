import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write
N, M = map(int, input().rstrip().split())
matrix = [[*map(int, input().rstrip().split())] for _ in range(N)]
ij_list = [
    tuple(map(lambda x: x - 1, map(int, input().rstrip().split()))) for _ in range(M)
]

row_prefix_sums = []
col_prefix_sums = []

for i in range(N):
    row_sum = 0
    row_sum_list = []
    for j in range(N):
        row_sum += matrix[i][j]
        row_sum_list.append(row_sum)
    row_prefix_sums.append(row_sum_list)


for i_start, j_start, i_end, j_end in ij_list:
    if i_start == i_end and j_start == j_end:
        print(f"{matrix[i_start][j_start]}\n")
        continue
    ans = 0
    for row in range(i_start, i_end + 1):
        if j_start - 1 >= 0:
            ans += row_prefix_sums[row][j_end] - row_prefix_sums[row][j_start - 1]
        else:
            ans += row_prefix_sums[row][j_end]
    print(f"{ans}\n")
