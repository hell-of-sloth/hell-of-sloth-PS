import sys

N, B = sys.stdin.readline().split()

B = int(B)
N = list(N)
num = []
jinsu = [str(i) for i in range(10)] + [chr(i) for i in range(65, 91)]
result = 0

for i in range(len(N)):
    num.append(B**i)
num.reverse()
     
for i in range(len(num)):
    result += jinsu.index(N[i]) * num[i]

print(result)