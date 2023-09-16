import sys

string = sys.stdin.readline().rstrip()
explosion_str = sys.stdin.readline().rstrip()

def string_explosion(): # 폭발은 예술이다
    global string, explosion_str
    
    while True:
        if explosion_str not in string: # 폭발 문자열이 더이상 없으면 종료
            break
        string = string.replace(explosion_str, '') # 폭발 문자열 제거
        
    if string == '': # 빈 문자열이면 FRULA 출력
        print('FRULA')
    else:            # 아니면 문자열 출력
        print(string)
        
string_explosion()