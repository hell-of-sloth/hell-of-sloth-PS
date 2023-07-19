N = int(input())

plot = [0]*(N+1)

for i in range(1, N):
    if plot[i+1] == 0:
            plot[i+1] = plot[i]+1
    else:
        plot[i+1]=min(plot[i+1], plot[i]+1)

    if i*2<=N:
        if plot[i*2] == 0:
            plot[i*2] = plot[i]+1
        else:
            plot[i*2]=min(plot[i*2],plot[i]+1)

    if i*3<=N:
        if plot[i*3] == 0:
            plot[i*3] = plot[i]+1
        else:
            plot[i*3]=min(plot[i*3],plot[i]+1)
print(plot[N])