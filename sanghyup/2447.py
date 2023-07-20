import math
def findstar(n):
    if n ==1:
        return ['***','* *','***']
    
    star = findstar(n-1)
    newstar = []
    for line in star:
        newstar.append(line*3)
    for line in star:
         newstar.append(line+' '*len(line)+line)
    for line in star:
        newstar.append(line*3)
    return newstar
for i in findstar(round((math.log(int(input()),3)))):
        print(i)