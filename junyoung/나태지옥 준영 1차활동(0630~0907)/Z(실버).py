import math

N, r, c = map(int, input().split())

# 재귀함수 이용
def Z(x, y, start, end): # x: 가로, y: 세로, start: 시작값, end: 끝값
    num = end - start + 1 # num은 배열의 길이
    length = int(math.sqrt(num)) # 한 변의 길이
    
    if num == 4:
        if x == 0 and y == 0:
            return start
        elif x == 1 and y == 0:
            return start + 1
        elif x == 0 and y == 1:
            return start + 2
        elif x == 1 and y == 1:
            return start + 3
    
    if x < length // 2 and y < length // 2:
        return Z(x, y, start, start + (num // 4) - 1)
    elif x >= length // 2 and y < length // 2:
        return Z(x - length // 2, y, start + (num // 4), start + (num // 2) - 1)
    elif x < length // 2 and y >= length // 2:
        return Z(x, y - length // 2, start + (num // 2), start + (num * 3 // 4) - 1)
    elif x >= length // 2 and y >= length // 2:
        return Z(x - length // 2, y - length // 2, start + (num * 3 // 4), end)
    
print(Z(c, r, 0, 2 ** (2 * N) - 1))