import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N = input().rstrip()
if len(N) == 1:
    N = "0" + N

ans = 0
iteration_trigger = True
prev_N = N
while iteration_trigger:
    new_num = prev_N[1] + (str(int(prev_N[0]) + int(prev_N[1])))[-1]
    # print(f"{new_num}\n")
    ans += 1
    if new_num == N:
        iteration_trigger = False
        continue

    prev_N = new_num

print(f"{ans}\n")
