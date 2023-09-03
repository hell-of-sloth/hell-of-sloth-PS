from sys import stdin, stdout

input = stdin.readline
print = stdout.write

A, B = map(int, input().rstrip().split())

if A == B:
    print("==\n")

else:
    print(f"{'>' if A > B else '<'}\n")
