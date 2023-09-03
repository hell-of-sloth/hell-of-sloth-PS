import sys

input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())

ans = 0


def is_han_num(num):
    def is_arithmetic_seq(num_str):
        if len(num_str) == 1:
            return True

        seq = list(map(int, num_str))
        difference = seq[1] - seq[0]

        for i in range(len(seq)):
            if i == (len(seq) - 1):
                return True
            if seq[i + 1] - seq[i] != difference:
                return False

    return is_arithmetic_seq(str(num))


for i in range(1, N + 1):
    if is_han_num(i):
        ans += 1

print(f"{ans}\n")
