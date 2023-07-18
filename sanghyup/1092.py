NoC = int(input())
crane = list(map(int, input().split()))
NoB = int(input())
box = list(map(int, input().split()))

crane.sort(reverse = True)
box.sort(reverse = True)

time = 0 
moved = [0 for _ in range(NoB)]
count = 0 

next = [0 for _ in range(NoC)]

if max(box) > max(crane):
    print(-1)
else:
    while count < len(box):
        for i in range(NoC):
            while next[i] < len(box):
                if not moved[next[i]] and crane[i] >= box[next[i]]:
                    moved[next[i]] = True
                    next[i] += 1
                    count += 1
                    break
                next[i] += 1
        time += 1
    print(time)    