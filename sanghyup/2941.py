word = input()

check = ''
check2 = False
count =0
for i in word:
    if (check =='c' or check=='z' or check =='s') and i == '=':
        count-=1
        if check2 ==True:
            count-=1
    check2=False
    if (check == 'c' or check =='d') and i=='-':
        count-=1
    elif (check == 'l' or check=='n') and i=='j':
        count-=1
    elif (check=='d') and i=='z':
        check2 =True
    if i=='c' or i=='d' or i=='l' or i=='n' or i=='s' or i=='z':
        check = i
    else:
        check = ''
    count+=1
print(count)