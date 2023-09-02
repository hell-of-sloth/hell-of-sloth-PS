def mergesort(p, r):
    global arr, count
    if (p<r and count <=K):
        q = (p+r)//2
        mergesort(p, q)
        mergesort(q+1, r)
        merge(p , q , r)

def merge(p, q, r):
    global arr, count, result
    tmp = [0 for i in range(r-p+1)]
    i, j, t = p, q+1, 0
    while i<= q and j<=r:
        if (arr[i]<= arr[j]):
            tmp[t] = arr[i]
            t+=1
            i+=1
        else:
            tmp[t] = arr[j]
            t+=1
            j+=1
    while(i<=q):
        tmp[t] = arr[i]
        t+=1
        i+=1
    while(j<=r):
        tmp[t] = arr[j]
        t+=1
        j+=1
    i = p
    t = 0
    while(i<=r):
        arr[i] = tmp[t]
        count +=1
        if count == K:
            result = arr[i]
            break
        i+=1
        t+=1

end, K= map(int, input().split())
arr = [i for i in map(int, input().split())]
count = 0
result = -1
mergesort(0, end-1)
print(result)
