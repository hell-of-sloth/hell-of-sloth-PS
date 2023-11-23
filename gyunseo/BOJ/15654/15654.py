import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write


N, M = map(int, input().rstrip().split())
numbers = [*sorted(map(int, input().rstrip().split()))]
# print(f"{numbers}\n")


def print_lexicographical_order():
    def dfs(cur, already_picked, depth):
        already_picked.append(cur)
        if depth == M - 1:
            to_be_printed = " ".join(map(str, already_picked))
            print(f"{to_be_printed}\n")
            return
        for next_num in numbers:
            if next_num in already_picked:
                continue
            dfs(next_num, [*already_picked], depth + 1)

    for num in numbers:
        dfs(num, [], 0)


print_lexicographical_order()
