'''
S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 
다음과 같은 두 가지 연산만 가능하다.

문자열의 뒤에 A를 추가한다.
문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 
알아내는 프로그램을 작성하시오. 
(T -> S 로 감소형)
'''

import sys

S = sys.stdin.readline().strip()
T = sys.stdin.readline().strip()

result = 0

def transformer(T):
    global S, result
    
    if len(S) == len(T):
        if S == T:
            result = 1
        return
            
    else:
        if T[0] == 'B':
            transformer(T[1:][::-1])
        if T[-1] == 'A':
            transformer(T[:-1])

transformer(T)

print(result)