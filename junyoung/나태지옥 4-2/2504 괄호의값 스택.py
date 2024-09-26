# 체감 난이도 4/10, 문제보자마자 스택 사용이란것을 알았음, 근데 예외조건 설정이 생각보다 까다롭

import sys

s = sys.stdin.readline().strip()

def st():
    global s
    stk = []

    for c in s:
        # 여는 괄호일 때
        if c == '(' or c == '[':
            stk.append(c)
        # 닫는 괄호일 때
        elif c == ')':
            # 스택이 비어있거나 짝이 안맞으면 0
            if not stk or stk[-1] == '[':
                print(0)
                return
            
            # 짝이 맞을 때
            if stk[-1] == '(':
                stk.pop()
                stk.append(2)
                
            # 바로 ( 가 안나오면 안에 숫자가 있거나 안 맞는 경우임
            else:
                temp = 0 # 숫자를 저장할 변수
                while True:
                    if not stk or stk[-1] == '[': # 하면서 스택이 비거나 다른 괄호가 나오면 0
                        print(0)
                        return
                    if stk[-1] == '(': # 짝이 맞으면 숫자를 스택에 넣고 끝
                        stk.pop()
                        break
                    else:
                        temp += stk.pop() # 숫자를 더해줌
                stk.append(temp * 2)
        # 닫는 대괄호일 때, 위랑 매커니즘 같음
        elif c == ']':
            if not stk or stk[-1] == '(':
                print(0)
                return
            
            if stk[-1] == '[':
                stk.pop()
                stk.append(3)
            else:
                temp = 0
                while True:
                    if not stk or stk[-1] == '(':
                        print(0)
                        return
                    if stk[-1] == '[':
                        stk.pop()
                        break
                    else:
                        temp += stk.pop()
                stk.append(temp * 3)
    
    # 다 돌면 안에 숫자만 있어야 함       
    if '(' in stk or '[' in stk:
        print(0)
        return
    else:
        print(sum(stk)) # 결과
        
st()