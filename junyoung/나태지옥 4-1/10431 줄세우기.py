import sys

P = int(sys.stdin.readline().strip())

tcase = [[] for _ in range(P+1)]

for _ in range(P):
    inp = list(map(int, sys.stdin.readline().split()))
    tcase[inp[0]].extend(inp[1:])
    
for i in range(1, P+1):
    temp = []
    total_count = 0
    while tcase[i]:
        if not temp:
            temp.append(tcase[i].pop(0))
        else:
            flag = False
            count = 0
            for j in range(len(temp)-1, -1, -1):
                if temp[j] < tcase[i][0]:
                    if j == len(temp)-1:
                        temp.append(tcase[i].pop(0))
                        flag = True
                        break
                    temp.insert(j+1, tcase[i].pop(0))
                    total_count += count
                    flag = True
                    break
                count += 1
            if not flag:
                temp.insert(0, tcase[i].pop(0))
                total_count += count
                
    print(i, total_count)
                