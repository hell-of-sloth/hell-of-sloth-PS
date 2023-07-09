N = int(input())
before_bulb = list(map(int, input()))
after_bulb = list(map(int, input()))

# 개어렵네

def Switch(A, B):
    temp_A = A[:]
    press = 0
    for i in range(1, N):
        
        if temp_A[i-1] == B[i-1]:
            continue
        
        press += 1
        for j in range(i-1, i+2):
            if j<N:
                temp_A[j] = 1 - temp_A[j]
    if temp_A == B:
        return press 
    else:
        return float('inf')

result = Switch(before_bulb, after_bulb)

before_bulb[0] = 1 - before_bulb[0]
before_bulb[1] = 1 - before_bulb[1]

result = min(result, Switch(before_bulb, after_bulb) + 1) # min(첫번째 전구x, 첫번째 전구 o)
if result != float('inf'):
    print(result)
else:
    print(-1)