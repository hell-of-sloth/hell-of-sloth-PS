N = int(input())
c = sorted(list(map(int, input().split())), reverse=True) # 크레인 무게, 내림차순
M = int(input())
b = sorted(list(map(int, input().split())), reverse=True) # 박스 무게, 내림차순


def crane_box(crane, box):
    
    if crane[0] < box[0]: # 크레인이 들 수 있는 무게가 박스보다 작으면
        print(-1)
        return
    count = 0

    while box: # 박스가 없어질 때까지
        count += 1
        for i in range(N):
            for j in range(len(box)):
                if crane[i] >= box[j]:
                    del box[j]
                    break
                
    print(count)
    
crane_box(c, b)