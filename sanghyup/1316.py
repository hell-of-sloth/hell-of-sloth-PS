def groupword(word):
    used=set()
    temp =''
    for i in word:
        if i == temp:
            continue
        if i not in used:
            used.add(i)
        else:
            return 0
        temp =i
    return 1

n = int(input())
count = 0
for _ in range(n):
    count+=groupword(input())
print(count)
