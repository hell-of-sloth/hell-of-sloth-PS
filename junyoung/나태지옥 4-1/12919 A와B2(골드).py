'''
S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 
다음과 같은 두 가지 연산만 가능하다.

문자열의 뒤에 A를 추가한다.
문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 
알아내는 프로그램을 작성하시오. 
(S -> T 로 증가형)
'''


import sys

def transformer(S, T):
    if len(S) == len(T):
        return 1 if S == T else 0
    else:
        if T.find(S) != -1 or T.find(S[::-1]) != -1:
            return transformer(S + 'A', T) or transformer('B' + S[::-1], T)
        else:
            return 0
        
S = sys.stdin.readline().strip()
T = sys.stdin.readline().strip()

print(transformer(S, T))