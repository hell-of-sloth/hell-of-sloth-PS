import sys

n = int(sys.stdin.readline().strip())

for _ in range(n):
    count = 0
    PS = sys.stdin.readline().strip()
    for letter in PS:
        if letter == '(':
            count += 1
        elif letter == ')' and count > 0:
            count -= 1
        else:
            count = 1
            break
    if count == 0:
        print('YES')
    else:
        print('NO')