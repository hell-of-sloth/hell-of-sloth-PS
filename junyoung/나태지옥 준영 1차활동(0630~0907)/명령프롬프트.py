import sys

files = []
N = int(sys.stdin.readline().rstrip())
for _ in range(N):
    files.append(sys.stdin.readline().rstrip())
    
result = ''
    
for i in range(len(files[0])):
    flag = 0
    for j in range(1, len(files)):
        if files[0][i] != files[j][i]:
            result += '?'
            flag = 1
            break
    if flag == 0:
        result += files[0][i]

print(result)
    