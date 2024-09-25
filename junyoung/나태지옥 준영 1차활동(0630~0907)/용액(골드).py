N = int(input())

liquid = list(map(int, input().split()))

def Liquid_Search():
    global liquid, N
    
    abs_value = 2000000000
    n1, n2 = 0, 0
    start = 0
    end = N - 1
    
    while start < end:
        value = liquid[start] + liquid[end]
        
        if abs(value) < abs_value:
            n1, n2 = liquid[start], liquid[end]
            abs_value = abs(value)
        
        if value <= 0:
            start += 1
        else:
            end -= 1
            
    print(n1, n2)
    
Liquid_Search()