import sys

input = sys.stdin.readline

def findgcd(a,b):
    while(a%b):
        a, b = b, a%b
    return b

n = int(input())

prev = int(input())
gap = []

for i in range(n-1):
    now = int(input())
    gap.append(now-prev)
    prev = now


gcd = gap[0]
for i in gap:
    gcd = findgcd(gcd, i)

ans = 0
for i in gap:
    ans+=i//gcd-1
print(ans)