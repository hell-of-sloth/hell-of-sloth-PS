import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

alphabet_count = {chr(ord("A") + i): 0 for i in range(26)}


def count_alphabet(alphabet):
    alphabet_count[alphabet] += 1


s = list(input().rstrip().upper())
# 왜 그냥 map만 쓰면 적용이 안될까???
[*map(count_alphabet, s)]

max_cnt_alphabet = max(alphabet_count, key=alphabet_count.get)
max_cnt = max(alphabet_count.values())
if len([1 for cnt in alphabet_count.values() if cnt == max_cnt]) >= 2:
    print(f"?\n")
else:
    print(f"{max_cnt_alphabet}\n")
