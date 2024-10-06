# 체감 난이도 6/10, 계산을 어떻게 해야할지 꽤나 고민했던 문제, 생각나도 구현하는데 시간도 좀 든다
# 먼저 괄호의 조건이 까다롭 -> 먼저 괄호 나올수 있는 것을 백트랙킹으로 구함 -> add_ops
# 그 다음 괄호가 있는 것을 적용한 계산을 실행 -> calcul
# 일일히 다 구하는 브루트포스 문제이다, 삼성은 꽤 까다롭다... 그래도 맞았다!

import sys

N = int(sys.stdin.readline().strip())
com = list(sys.stdin.readline().strip())
ops = [[i] for i in range(1, N, 2)]

result = float('-inf')

def add_ops(op):
    temp = op[:]
    
    for i in range(op[-1] + 2, N, 2):
        if i-2 in op:
            continue
        else:
            temp.append(i)
            ops.append(temp[:])
            add_ops(temp)
            temp.pop()
    return

def calcul(command):
    global com
    
    temp = []
    ans = 0
    i = 0
    while i < len(com):
        if i in command:
            temp.pop()
            if com[i] == '+':
                temp.append(int(com[i-1]) + int(com[i+1]))
            elif com[i] == '-':
                temp.append(int(com[i-1]) - int(com[i+1]))
            elif com[i] == '*':
                temp.append(int(com[i-1]) * int(com[i+1]))
            i += 1
        else:
            if com[i].isdigit():
                temp.append(int(com[i]))
            else:
                temp.append(com[i])
        i += 1
    
    ans = temp[0]
     
    for i in range(1, len(temp), 2):
        if temp[i] == '+':
            ans += temp[i+1]
        elif temp[i] == '-':
            ans -= temp[i+1]
        elif temp[i] == '*':
            ans *= temp[i+1]
            
    return ans
            
            
for op in ops[:]:
    add_ops(op)

for op in ops:
    result = max(calcul(op), result)

if N == 1:
    print(com[0])
else:   
    print(result)