'''
가희가 3번 건물을 볼 수 없는 이유는 3번 건물 왼쪽에 있는
2번 건물의 높이가 3번 건물보다 높기 때문입니다. 그리고, 
단비가 1번 건물을 볼 수 없는 이유는 1번 건물보다 오른쪽에 있는 
2번 건물과 3번 건물이 1번 건물보다 높기 때문입니다.
가희와 단비 사이에 있는 건물의 개수 N과 가희가 볼 수 있는 건물의 개수 a,
단비가 볼 수 있는 건물의 개수 b가 주어집니다.
사전 순으로 가장 앞서는 N개의 건물 높이 정보를 출력해 주세요.
'''

import sys

N, a, b = map(int, sys.stdin.readline().split())

def gahee(N, a, b):
    buildings = []
    
    if a > b: # 가희가 더 많이 볼 수 있는 경우
        for i in range(1, a + 1):
            buildings.append(i)
        for i in range(b-1, 0, -1):
            buildings.append(i)
    else: # 단비가 더 많이 볼 수 있는 경우
        for i in range(1, a):
            buildings.append(i)
        for i in range(b, 0, -1):
            buildings.append(i)
            
    if N > len(buildings): # 건물의 개수가 더 많은 경우
        for i in range(N - len(buildings)):
            buildings.insert(1, 1)
            
    if N == len(buildings): # 건물의 개수가 같은 경우
        return buildings
    else:
        return [-1]

print(" ".join(map(str, gahee(N, a, b))))