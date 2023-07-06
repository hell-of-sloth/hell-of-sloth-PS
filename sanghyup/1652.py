import sys

def invertMatrix(matrix):
    matrix = list(zip(*matrix))
    inverse = []
    for line in matrix:
        line = ''.join(line)
        inverse.append(line)
    return inverse

def getSpace(matrix):
    count =0
    for line in matrix:
        line = line.split('X')
        for space in line:
            if '..' in space:
                count += 1
    return count


n = int(sys.stdin.readline())
rowroom = []
for i in range(n):
    rowroom.append(sys.stdin.readline().strip())

colroom = invertMatrix(rowroom)

print(getSpace(rowroom), getSpace(colroom))