import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline

print = sys.stdout.write


a, b = map(int, input().rstrip().split())

if a < b:
    a, b = b, a


def get_gcd(x, y):
    while y:
        tmp = y
        if x % y == 0:
            return y
        y = x % y
        x = tmp

    return y


gcd = get_gcd(a, b)
print(f"{gcd}\n")
lcm = gcd * (a // gcd) * (b // gcd)
print(f"{lcm}\n")
