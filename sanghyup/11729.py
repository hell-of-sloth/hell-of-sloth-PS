def hanoi(n, start, end):
    global count, ans
    count+=1
    if n ==1:
        ans.append((start, end))
        return
    
    hanoi(n-1, start, 6-(start+end))
    ans.append((start, end))
    hanoi(n-1, 6-(start+end), end)

count = 0
ans = []
hanoi(int(input()), 1, 3)
print(count)
for i in ans:
    print(*i)