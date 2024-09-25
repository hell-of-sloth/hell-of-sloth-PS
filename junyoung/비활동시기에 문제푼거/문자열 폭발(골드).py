import sys

string = sys.stdin.readline().rstrip()
explosion_str = sys.stdin.readline().rstrip()

def string_explosion():
    global string, explosion_str

    stack = []  # 스택을 사용하여 문자열을 관리

    for char in string:
        stack.append(char)  # 스택에 문자 추가

        # 현재 스택의 끝 문자열이 폭발 문자열의 마지막 문자와 같다면 확인
        if char == explosion_str[-1] and ''.join(stack[-len(explosion_str):]) == explosion_str:
            # 폭발 문자열 제거
            del stack[-len(explosion_str):]

    result = ''.join(stack)
    
    if not result:
        print('FRULA')
    else:
        print(result)

string_explosion()