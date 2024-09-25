import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())

cnt = 0
user_infos = {}


def parse_raw_data(number_str, name_str):
    global cnt
    cnt += 1
    return int(number_str), cnt, name_str


user_infos = [parse_raw_data(*input().rstrip().split()) for _ in range(N)]

for user_info in sorted(user_infos):
    print(f"{user_info[0]} {user_info[2]}\n")
