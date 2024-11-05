# 체감 난이도 4/10, 스택을 이용한 후위 계산법과 계산 문제
# 후위계산법으로 바꾸는 코드를 모르면 풀기 힘들다


def back_calcul_transformer(L):
    stk = []
    result = []
    
    for i in range(len(L)):
        if L[i].isdigit(): # 숫자는 바로 결과에 넣기
            result.append(L[i])
        elif L[i] == '+': # 우선순위가 낮은 연산자는 스택에서 빼고 넣기
            while stk:
                if stk[-1] == '(': # 괄호가 있으면 괄호까지만
                    break
                result.append(stk.pop())
            stk.append(L[i])    
        elif L[i] == '*': # 우선순위가 높은 연산자는 스택에 넣기만 하기
            stk.append(L[i])
        elif L[i] == '(':
            stk.append(L[i])
        elif L[i] == ')': # 괄호가 닫히면 괄호까지만 빼기
            while stk[-1] != '(':
                result.append(stk.pop())
            stk.pop()
    while stk:
        result.append(stk.pop())
        
    return result

def solve():
    N = int(input())
    L = input()
    backnum = back_calcul_transformer(L)
    stk = []
    for i in range(len(backnum)):
        if backnum[i].isdigit():
            stk.append(int(backnum[i]))
        elif backnum[i] == '+':
            stk.append(stk.pop() + stk.pop())
        elif backnum[i] == '*':
            stk.append(stk.pop() * stk.pop())
            
    return stk[0]

for t in range(1, 11):
    print('#{} {}'.format(t, solve()))