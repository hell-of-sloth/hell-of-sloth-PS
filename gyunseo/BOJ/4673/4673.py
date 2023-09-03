import sys

input = sys.stdin.readline
print = sys.stdout.write

is_self_number = [True for _ in range(10_000 + 1)]


iteration_trigger = True


def get_self_num(num):
    ret_sum = num
    divide_num = 10
    while num:
        ret_sum += num % divide_num
        num = num // divide_num

    return ret_sum


def mark_nonselfnum_until_10_000(num):
    while True:
        nn = get_self_num(num)
        if nn > 10_000 or is_self_number[nn] == False:
            break
        is_self_number[nn] = False
        num = nn


for i in range(1, 10_000 + 1):
    # seed가 valid한지 검사
    if is_self_number[i] == True:
        mark_nonselfnum_until_10_000(i)


for i in range(1, 10_000 + 1):
    if is_self_number[i]:
        print(f"{i}\n")
