import sys

# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
factorial_res = 1
for i in range(2, N + 1):    
    factorial_res *= i

reversed_factorial_res_str = f'{factorial_res}'[::-1]

ans = 0 
for ch in reversed_factorial_res_str:
    if ch != '0':
        break

    ans+=1

print(f'{ans}\n')