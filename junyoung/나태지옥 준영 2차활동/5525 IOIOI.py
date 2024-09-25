import sys

N = int(sys.stdin.readline().strip())
M = int(sys.stdin.readline().strip())
S = sys.stdin.readline().strip()

answer = 0

start = 0
check = True

for i in range(1, M):
    if i == 1:
        if S[i-1] == 'O':
            check = False
            
    if check == False:
        if S[i] == 'I':
            start = i
            check = True
        continue
    else:
        if S[i] == S[i-1]:
            if S[i] == 'I':
                start = i
            else:
                check = False
        else:
            if S[i] == 'I':
                if i - start >= 2*N:
                    answer += 1
                    
print(answer)