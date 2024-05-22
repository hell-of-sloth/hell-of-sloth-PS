import sys

T = int(sys.stdin.readline().strip())


def check(a, b, c):
    dist = 0
    
    for i in range(4):
        if a[i] != b[i]:
            dist += 1
        if a[i] != c[i]:
            dist += 1
        if b[i] != c[i]:
            dist += 1
            
    return dist

for _ in range(T):
    n = int(sys.stdin.readline().strip())
    arr = sys.stdin.readline().split()
    
    min_dist = 1000000
    
    if n > 32:
        print(0)
        continue
    
    for i in range(n-2):
        for j in range(i+1, n-1):
            for k in range(j+1, n):
                dist = check(arr[i], arr[j], arr[k])
                if dist < min_dist:
                    min_dist = dist
                    
    print(min_dist)

    
    