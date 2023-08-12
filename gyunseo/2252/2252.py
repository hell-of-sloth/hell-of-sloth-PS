import sys
from collections import deque

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write


# N = 노드 개수, M = 간선 개수
N, M = map(int, input().rstrip().split())
students = [[] for _ in range(N + 1)]
in_degree = [0 for _ in range(N + 1)]
for _ in range(M):
    A, B = map(int, input().rstrip().split())
    students[A].append(B)
    in_degree[B] += 1

# print(f"{students}\n{in_degree}\n")


def topology_sort():
    res = []
    q = deque()

    for i in range(1, N + 1):
        if in_degree[i] == 0:
            q.append(i)

    while q:
        cur = q.popleft()
        res.append(cur)

        # cur와 연결된 노드의 진입 차수 한개씩 차감
        for next_student in students[cur]:
            in_degree[next_student] -= 1
            if in_degree[next_student] == 0:
                q.append(next_student)

    return res


print(f"{' '.join(map(str, topology_sort()))}\n")
