string = input()

# 중위 표기식을 후위 표기식으로 변환 (스택 이용)
def Postfix_Notation():
    stack = []
    result = ''
    for i in string:
        if i.isalpha():
            result += i
        else:
            if i == '(': 
                stack.append(i)
            elif i == '*' or i == '/':              # *, /는 스택에 있는 *, /만 pop
                while stack and (stack[-1] == '*' or stack[-1] == '/'):
                    result += stack.pop()
                stack.append(i)
            elif i == '+' or i == '-':              # +, -는 스택에 있는 모든 연산자를 pop
                while stack and stack[-1] != '(':
                    result += stack.pop()
                stack.append(i)
            elif i == ')':                          # 괄호 끝나면 팝
                while stack and stack[-1] != '(':
                    result += stack.pop()
                stack.pop()
    while stack:                                    # 스택에 남아있는 연산자 모두 pop
        result += stack.pop()
    return result

print(Postfix_Notation())