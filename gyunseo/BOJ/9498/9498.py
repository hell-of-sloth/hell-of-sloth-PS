import sys

input = sys.stdin.readline
print = sys.stdout.write

score = int(input().rstrip())
ret = None
if score >= 90 and score <= 100:
    ret = "A"
elif score >= 80:
    ret = "B"
elif score >= 70:
    ret = "C"
elif score >= 60:
    ret = "D"
else:
    ret = "F"

print(f"{ret}\n")
