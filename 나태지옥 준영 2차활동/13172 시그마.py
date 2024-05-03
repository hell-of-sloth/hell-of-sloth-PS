import sys

MOD = 1000000007

M = int(sys.stdin.readline().strip())

def cal(N, x):
  if x == 1:
    return N

  v = cal(N, x // 2)
  
  if x % 2 == 0:
    return v * v % MOD
  else:
    return v * v * N % MOD

count = 0
for i in range(M):
  N, S = map(int, sys.stdin.readline().split())
  new = cal(N, MOD-2)
  count = (count + new * S % MOD) % MOD

print(count)