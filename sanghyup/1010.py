import math
import sys
iter = int(sys.stdin.readline())

for _ in range(iter):
    n, m = map(int, sys.stdin.readline().split())
    bridge = math.factorial(m) // (math.factorial(n) * math.factorial(m - n))
    print(bridge)