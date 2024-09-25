import sys

N, C = map(int, sys.stdin.readline().split())

house = []

for i in range(N):
    house.append(int(sys.stdin.readline().rstrip()))
    
house.sort()
    
def Binary_Search(house, C):
    start = 1
    end = house[-1] - house[0]
    result = 0
    
    while start <= end:
        mid = (start + end) // 2
        value = house[0]
        count = 1
        
        for i in range(1, len(house)):
            if house[i] >= value + mid:
                value = house[i]
                count += 1
        
        if count >= C:
            start = mid + 1
            result = mid
        else:
            end = mid - 1
            
    return result

print(Binary_Search(house, C))