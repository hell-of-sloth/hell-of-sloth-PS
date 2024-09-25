import sys

M = int(input())

S = set()

def ADD(x): # x를 집합에 추가
    global S
    
    if x not in S:
        S.add(x)
        
def REMOVE(x): # x를 집합에서 제거
    global S
    
    if x in S:
        S.remove(x)
        
def CHECK(x): # x가 집합에 있는지 확인
    global S
    
    if x in S:
        print(1) # 있으면 1
    else:
        print(0) # 없으면 0

def TOGGLE(x): # x가 집합에 있으면 제거, 없으면 추가
    global S
    
    if x in S:
        S.remove(x)
    else:
        S.add(x)
        
def ALL(): # 집합을 {1, 2, ..., 20}으로 변경
    global S
    
    S.clear()
    S.update([i for i in range(1, 21)])
    
def EMPTY(): # 집합을 공집합으로 변경
    global S
    
    S.clear()
    
    
# 메인
for i in range(M):
    command = sys.stdin.readline().split()
    
    if command[0] == 'add':
        ADD(int(command[1]))
    elif command[0] == 'remove':
        REMOVE(int(command[1]))
    elif command[0] == 'check':
        CHECK(int(command[1]))
    elif command[0] == 'toggle':
        TOGGLE(int(command[1]))
    elif command[0] == 'all':
        ALL()
    elif command[0] == 'empty':
        EMPTY()