x, y = map(int, input().split())

ans = [1 for _ in range(y)]

counter = -1

while(ans[0]<=x):
    print(*ans)
    ans[counter]+=1
    if ans[counter]>x:
        while True:
            counter-=1
            if counter < -1*y:
                exit()
            ans[counter]+=1
            if counter<-1:
                ans[counter+1::] = [1]*(-1*counter-1)
            if ans[counter]<=x: break
    counter = -1