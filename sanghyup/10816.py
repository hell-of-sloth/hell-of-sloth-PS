dic = {}
_= input()
for card in input().split():
    if card in dic:
        dic[card]+=1
    else:
        dic[card] = 1
_ = input()
for i in input().split():
    print(dic[i] if i in dic else 0, end = ' ')
