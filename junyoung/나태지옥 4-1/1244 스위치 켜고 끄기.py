'''
설명이 길어서 링크로 남김
https://www.acmicpc.net/problem/1244
'''

import sys

switch_N = int(sys.stdin.readline().strip())

switch_state = [0] + list(map(int, sys.stdin.readline().split()))

student_N = int(sys.stdin.readline().strip())

students = []

for i in range(student_N):
    students.append(tuple(map(int, sys.stdin.readline().split())))
    
def man(num):
    global switch_state
    
    for i in range(num, switch_N + 1, num):
        switch_state[i] = 1 - switch_state[i]
    
def woman(num):
    global switch_state
    
    switch_state[num] = 1 - switch_state[num]
    for i in range(1, min(switch_N - num + 1, num)):
        if switch_state[num - i] == switch_state[num + i]:
            switch_state[num - i] = 1 - switch_state[num - i]
            switch_state[num + i] = 1 - switch_state[num + i]
        else:
            break
        
for student in students:
    if student[0] == 1:
        man(student[1])
    else:
        woman(student[1])
        
for i in range(1, switch_N + 1):
    print(switch_state[i], end = ' ')
    if i % 20 == 0:
        print()