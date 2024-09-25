import sys
import itertools

L, C = map(int, sys.stdin.readline().split())

alpha = list(map(str, sys.stdin.readline().split()))
jaum = [] # 자음
moum = [] # 모음
result_list = [] # 결과 리스트

for letter in alpha: # 자음, 모음 분리
    if letter == 'a' or letter == 'e' or letter == 'i' or letter == 'o' or letter == 'u':
        moum.append(letter)
    else:
        jaum.append(letter)
        
for j in range(L-1, 1, -1): # 자음, 모음 조합
    # j : 자음 개수
    # m : 모음 개수
    m = L - j
    ja = list(map(''.join, itertools.combinations(jaum, j))) # 자음 조합
    mo = list(map(''.join, itertools.combinations(moum, m))) # 모음 조합
    for x in ja:
        for y in mo:
            result = x + y
            sorted_result = sorted(result) # 정렬
            new = ''.join(sorted_result)
            result_list.append(new) # 결과 리스트에 추가
            
result_list.sort() # 정렬     
for i in result_list:
    print(i)