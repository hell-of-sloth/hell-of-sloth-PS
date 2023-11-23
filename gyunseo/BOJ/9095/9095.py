import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write

T = int(input().rstrip())


# back tracking
def get_num_of_cases(N):
    cnt = 0

    def dfs(node, node_sum):
        if node_sum == N:
            nonlocal cnt
            cnt += 1
            return

        if node_sum > N:
            return

        for next_node in [1, 2, 3]:
            dfs(next_node, node_sum + next_node)

    dfs(1, 1)
    dfs(2, 2)
    dfs(3, 3)

    return cnt


for _ in range(T):
    n = int(input().rstrip())
    print(f"{get_num_of_cases(n)}\n")
