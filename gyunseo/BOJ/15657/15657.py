import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N, M = map(int, input().rstrip().split())
numbers = [*sorted(map(int, input().rstrip().split()))]


def print_sequences():
    to_be_printed_list = []

    def dfs(level, cur):
        to_be_printed_list.append(cur)
        if level == M:
            to_be_printed = " ".join(map(str, to_be_printed_list))
            print(f"{to_be_printed}\n")
            to_be_printed_list.pop()
            return
        for next_num in numbers:
            if next_num >= cur:
                dfs(level + 1, next_num)
        to_be_printed_list.pop()

    for num in numbers:
        dfs(1, num)


print_sequences()
