'''  
N명의 아이들이 임의의 순서로 줄을 서 있을 때, 번호 순서대로 
배치하기 위해 옮겨지는 아이의 최소 수를 구하는 프로그램을 작성하시오.
'''
import sys

N = int(sys.stdin.readline().rstrip())

children = []

for _ in range(N): # 가장 긴 연속 수열 찾기, 첨음부터 보면서 끝보다 크면 뒤에 붙이고 작으면 요소 교체
    if not children:
        children.append(int(sys.stdin.readline().rstrip()))
    else:
        child = int(sys.stdin.readline().rstrip())
        if child > children[-1]: # 뒤에 붙이기
            children.append(child)
        else:
            for i in range(len(children)): # 요소 교체 (교체해도 길이는 어차피 같음)
                if child <= children[i]:
                    children[i] = child
                    break

print(N - len(children)) # 전체 - 가장 긴 연속 수열 = 옮겨지는 아이의 최소 수