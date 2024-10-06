import sys

N, d, k, c = map(int, sys.stdin.readline().split())

sushi = [int(sys.stdin.readline().strip()) for _ in range(N)]

no_duple_sushi = []
check = [False] * (d+1)
max_num = 0

start = 0
finish = 0

while True:
    if check[sushi[finish % N]] == False:
        check[sushi[finish % N]] = True
        finish += 1
        if finish - start > k:
            check[sushi[start]] = False
            start += 1
    else:
        check[sushi[start]] = False
        start += 1
        
    if start == N:
        break
        
    if finish - start == k:
        if finish <= N:
            no_duple_sushi.append(list(sushi[start:finish]))
        else:
            no_duple_sushi.append(list(sushi[start:] + sushi[:finish % N]))
            
    if max_num < finish - start:
        max_num = finish - start
            
if no_duple_sushi:
    for i in no_duple_sushi:
        if c not in i:
            max_num += 1
            break
    print(max_num)
else:
    print(max_num)
