alphabets = input()
ans = set()

for i in range(1, len(alphabets)+1):
    for j in range(len(alphabets)):
        ans.add(alphabets[j:j+i])
print(len(ans))