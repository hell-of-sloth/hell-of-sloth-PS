import sys

# sys.stdin = open("input.txt", "r")s
input = sys.stdin.readline
print = sys.stdout.write


N, M = map(int, input().rstrip().split())

non_heard_people = {input().rstrip() for _ in range(N)}
non_seen_people = {input().rstrip() for _ in range(M)}
non_heard_seen_people = non_heard_people & non_seen_people

print(f"{len(non_heard_seen_people)}\n")
for name in sorted(non_heard_seen_people):
    print(f"{name}\n")
