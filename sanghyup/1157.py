word = input().upper()

alphabets=[0]*26
alphabets.append(-1)

for alphabet in word:
    alphabets[ord(alphabet)-ord('A')]+=1

ans1 = 26
ans2 = 26
for i in range(26):
    if alphabets[i]>=alphabets[ans1]:
        ans2 = ans1
        ans1 = i
if alphabets[ans1]==alphabets[ans2]:
    print('?')
else:
    print(chr(ord('A')+ans1))
