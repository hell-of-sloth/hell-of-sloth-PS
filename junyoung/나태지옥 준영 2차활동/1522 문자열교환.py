'''
a와 b로만 이루어진 문자열이 주어질 때,  
a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를
최소로 하는 프로그램을 작성하시오.

이 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해 있는 것이다.

예를 들어,  aabbaaabaaba이 주어졌을 때, 2번의 교환이면 a를 모두 
연속으로 만들 수 있다.
'''

import sys

ab = sys.stdin.readline().strip()

b_count = ab.count('b')

max_b = 0

for i in range(len(ab)):
    end_index = (i+b_count) % len(ab)
    if end_index > i:
        temp_b = ab[i:end_index].count('b')
    else:
        temp_b = ab[i:].count('b') + ab[:end_index].count('b')
        
    if temp_b > max_b:
        max_b = temp_b
        
print(b_count - max_b)